package edu.ntnu.tobiasth.securitydashboard.check

import edu.ntnu.tobiasth.securitydashboard.service.GitHubService
import edu.ntnu.tobiasth.securitydashboard.service.HomeAssistantService
import edu.ntnu.tobiasth.securitydashboard.service.dto.Risk
import edu.ntnu.tobiasth.securitydashboard.util.AdvisoryUtil
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class AdvisoryCheck(
    val homeAssistantService: HomeAssistantService,
    val githubService: GitHubService,
    val advisoryUtil: AdvisoryUtil
) : Check() {
    override val id = "advisory-check"
    override val name = "Security Advisories"
    override val description = "Some software vulnerabilities get reported publicly to allow users to understand how to mitigate the risks to their system. By looking for security advisories one can ensure that Home Assistant is not running components with known unpatched vulnerabilities."
    override val mitigation = "The easiest way to avoid unpatched vulnerabilities is to keep components up-to-date. However, this is not always an option as it requires the maintainer of the component to release an update that patches the vulnerability. If there are no updates available, an alternative is to uninstall the component or look for workarounds for the vulnerability by searching for the vulnerability ID online."

    override fun check() {
        if (!githubService.isAvailable()) {
            yield(result(Risk.UNKNOWN, "Github API access has not been configured."))
            return
        }

        // Check core supervisor advisories and yield result
        val coreVersion = homeAssistantService.getCoreInfo().version
        val coreAdvisories = githubService.getActiveSecurityAdvisories(
            "home-assistant",
            "core",
            "Home Assistant Core",
            coreVersion
        )
        if (coreAdvisories.isEmpty()) {
            yield(result("Advisories for Home Assistant Core", Risk.LOW, "Core $coreVersion has no reported vulnerabilities."))
        } else {
            yield(result(
                "Advisories for Home Assistant Core",
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
            yield(result("Advisories for Home Assistant Supervisor", Risk.LOW, "Supervisor $supervisorVersion has no reported vulnerabilities."))
        } else {
            yield(result(
                "Advisories for Home Assistant Supervisor",
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
                        "Advisories for '${it.name}' add-on",
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
    }

}