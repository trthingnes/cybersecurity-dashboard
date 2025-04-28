package edu.ntnu.tobiasth.securitydashboard.check.impl

import edu.ntnu.tobiasth.securitydashboard.check.Check
import edu.ntnu.tobiasth.securitydashboard.check.Risk
import edu.ntnu.tobiasth.securitydashboard.client.dto.github.RepositorySecurityAdvisory
import edu.ntnu.tobiasth.securitydashboard.service.GitHubService
import edu.ntnu.tobiasth.securitydashboard.service.HomeAssistantService
import edu.ntnu.tobiasth.securitydashboard.util.VersionComparator
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class AdvisoryCheck(
    private val haService: HomeAssistantService,
    private val githubService: GitHubService,
    private val versionComparator: VersionComparator
) : Check() {
    override val id = "advisory-check"
    override val name = "Security Advisories"
    override val description = "Paying attention to publicly reported vulnerabilities for Home Assistant components can help you mitigate risks to your system."
    override val mitigation = "The easiest way to patch vulnerabilities is to keep components up-to-date. If this does not work, workarounds may be available in the vulnerability report."

    override fun check() {
        if (!githubService.isAvailable()) {
            yield(result(Risk.UNKNOWN, "Github API access has not been configured."))
            return
        }

        // Check core supervisor advisories and yield result
        val coreVersion = haService.getCoreInfo().version
        val coreAdvisories = githubService.getActiveSecurityAdvisories(
            "home-assistant",
            "core",
            "Home Assistant Core",
            coreVersion
        )
        if (coreAdvisories.isEmpty()) {
            yield(result("Advisories for Home Assistant Core", Risk.NONE, "Core $coreVersion has no reported vulnerabilities."))
        } else {
            yield(result(
                "Advisories for Home Assistant Core",
                getRisk(coreAdvisories),
                "Found unpatched vulnerabilities (${coreAdvisories.joinToString { it.cveId ?: it.ghsaId }}). Please update to Core ${
                    getPatchedVersion(coreAdvisories)
                }."
            ))
        }

        // Check supervisor advisories and yield result
        val supervisorVersion = haService.getSupervisorInfo().version
        val supervisorAdvisories = githubService.getActiveSecurityAdvisories(
            "home-assistant",
            "core",
            "Home Assistant Supervisor",
            supervisorVersion
        )
        if (supervisorAdvisories.isEmpty()) {
            yield(result("Advisories for Home Assistant Supervisor", Risk.NONE, "Supervisor $supervisorVersion has no reported vulnerabilities."))
        } else {
            yield(result(
                "Advisories for Home Assistant Supervisor",
                getRisk(supervisorAdvisories),
                "Found unpatched vulnerabilities (${supervisorAdvisories.joinToString { it.cveId ?: it.ghsaId }}). Please update to Supervisor ${
                    getPatchedVersion(supervisorAdvisories)
                }."
            ))
        }

        // Check add-on advisories and yield results
        val activeAddons = haService.getInstalledAddons()
        val activeRepositories = haService.getAddonRepositories()
            .filter { r -> activeAddons.any { a -> a.repository == r.slug } }
            .associateBy { it.slug }
        activeAddons.forEach {
            val repository = activeRepositories[it.repository]
            val advisories = if (repository != null && repository.source.contains("https://github.com/")) {
                val source = repository.source.substringAfter("https://github.com/").split("/")
                githubService.getActiveSecurityAdvisories(source.first(), source.last(), it.name, it.version)
            } else emptyList()
            if (advisories.isEmpty()) {
                yield(
                    result(
                        "Advisories for '${it.name}' add-on",
                        Risk.NONE,
                        "${it.name} ${it.version} has no reported vulnerabilities."
                    )
                )
            } else {
                yield(
                    result(
                        "Advisories for add-on '${it.name}'",
                        getRisk(advisories),
                        "Found unpatched vulnerabilities (${advisories.joinToString { a -> a.cveId ?: a.ghsaId }}). Please update to ${
                            getPatchedVersion(
                                advisories
                            )
                        }"
                    )
                )
            }
        }
    }

    fun getPatchedVersion(advisories: List<RepositorySecurityAdvisory>): String? {
        val filteredAdvisories = advisories
            .flatMap { it.vulnerabilities }
            .mapNotNull { it.patchedVersions }
            .sortedWith(versionComparator)

        return if (filteredAdvisories.isNotEmpty()) filteredAdvisories.last() else null
    }

    fun getRisk(advisories: List<RepositorySecurityAdvisory>) = if (advisories.any {
            it.severity == RepositorySecurityAdvisory.Severity.CRITICAL || it.severity == RepositorySecurityAdvisory.Severity.HIGH
        }) Risk.HIGH else Risk.MODERATE
}