package edu.ntnu.tobiasth.securitydashboard.check

import edu.ntnu.tobiasth.securitydashboard.service.GitHubService
import edu.ntnu.tobiasth.securitydashboard.service.HomeAssistantService
import edu.ntnu.tobiasth.securitydashboard.service.dto.CheckResult
import edu.ntnu.tobiasth.securitydashboard.service.dto.Risk
import edu.ntnu.tobiasth.securitydashboard.util.AdvisoryUtil
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class CoreAdvisoryCheck(
    val homeAssistantService: HomeAssistantService,
    val githubService: GitHubService,
    val advisoryUtil: AdvisoryUtil
) : Check {
    override val name: String
        get() = "Home Assistant Core Vulnerabilities"
    override val description: String
        get() = "Home Assistant Core does not have unpatched vulnerabilities reported in security advisories."

    override fun run(): CheckResult {
        val coreVersion = homeAssistantService.getCoreInfo().version
        val advisories = githubService.getActiveAdvisories(
            "home-assistant",
            "core",
            "Home Assistant Core",
            coreVersion
        )

        if (advisories.isEmpty()) {
            return result(Risk.LOW, "Home Assistant Core $coreVersion has no reported vulnerabilities.")
        }

        return result(
            advisoryUtil.getRisk(advisories),
            "Found unpatched vulnerabilities (${advisories.joinToString { it.cveId ?: it.ghsaId }}). Please update to Home Assistant Core ${
                advisoryUtil.getPatchedVersion(advisories)
            }."
        )
    }

}