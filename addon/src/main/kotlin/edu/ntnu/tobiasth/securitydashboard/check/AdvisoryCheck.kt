package edu.ntnu.tobiasth.securitydashboard.check

import edu.ntnu.tobiasth.securitydashboard.service.GitHubService
import edu.ntnu.tobiasth.securitydashboard.service.HomeAssistantService
import edu.ntnu.tobiasth.securitydashboard.service.dto.CheckResult
import edu.ntnu.tobiasth.securitydashboard.service.dto.Risk
import edu.ntnu.tobiasth.securitydashboard.util.AdvisoryUtil
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class AdvisoryCheck(
    val homeAssistantService: HomeAssistantService,
    val githubService: GitHubService,
    val advisoryUtil: AdvisoryUtil
) : Check() {
    override val name = "Security Advisories"
    override val description = "Home Assistant is not running with unpatched vulnerabilities reported in security advisories."

    override fun check(): CheckResult? {
        if (!githubService.isAvailable()) return result(Risk.UNKNOWN, "Github API access has not been configured.")

        // Check core supervisor advisories and yield result
        val coreVersion = homeAssistantService.getCoreInfo().version
        val coreAdvisories = githubService.getActiveSecurityAdvisories(
            "home-assistant",
            "core",
            "Home Assistant Core",
            coreVersion
        )
        if (coreAdvisories.isEmpty()) {
            yield(result(Risk.LOW, "Core $coreVersion has no reported vulnerabilities."))
        } else {
            yield(result(
                advisoryUtil.getRisk(coreAdvisories),
                "Found unpatched vulnerabilities (${coreAdvisories.joinToString { it.cveId ?: it.ghsaId }}). Please update to Core ${
                    advisoryUtil.getPatchedVersion(coreAdvisories)
                }."
            ))
        }

        // Check supervisor advisories and yield result
        val supervisorVersion = homeAssistantService.getSupervisorInfo().version
        val supervisorAdvisories = githubService.getActiveSecurityAdvisories(
            "home-assistant",
            "core",
            "Home Assistant Supervisor",
            supervisorVersion
        )
        if (supervisorAdvisories.isEmpty()) {
            yield(result(Risk.LOW, "Supervisor $supervisorVersion has no reported vulnerabilities."))
        } else {
            yield(result(
                advisoryUtil.getRisk(supervisorAdvisories),
                "Found unpatched vulnerabilities (${supervisorAdvisories.joinToString { it.cveId ?: it.ghsaId }}). Please update to Supervisor ${
                    advisoryUtil.getPatchedVersion(supervisorAdvisories)
                }."
            ))
        }

        // Check add-on advisories and yield results
        val activeAddons = homeAssistantService.getInstalledAddons()
        val activeRepositories = homeAssistantService.getAddonRepositories()
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
                        "Advisories for add-on '${it.name}'",
                        Risk.LOW,
                        "${it.name} ${it.version} has no reported vulnerabilities."
                    )
                )
            } else {
                yield(
                    result(
                        "Advisories for add-on '${it.name}'",
                        advisoryUtil.getRisk(advisories),
                        "Found unpatched vulnerabilities (${advisories.joinToString { a -> a.cveId ?: a.ghsaId }}). Please update to ${
                            advisoryUtil.getPatchedVersion(
                                advisories
                            )
                        }"
                    )
                )
            }
        }

        return null
    }

}