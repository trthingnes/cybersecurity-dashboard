package edu.ntnu.tobiasth.securitydashboard.check.impl

import edu.ntnu.tobiasth.securitydashboard.check.Check
import edu.ntnu.tobiasth.securitydashboard.check.dto.CheckResult
import edu.ntnu.tobiasth.securitydashboard.check.dto.Risk
import edu.ntnu.tobiasth.securitydashboard.client.SupervisorClient
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.rest.client.inject.RestClient

@ApplicationScoped
class SystemUpdateCheck(
    @RestClient val haClient: SupervisorClient
): Check {
    override val name: String
        get() = "System Update Check"
    override val description: String
        get() = "Check Home Assistant is up-to-date"

    override fun run(): CheckResult {
        val info = haClient.getCoreInfo().data
        if (info.updateAvailable) {
            return CheckResult(
                this,
                Risk.MODERATE,
                "A Home Assistant update is available (${info.versionLatest})."
            )
        }
        return CheckResult(this, Risk.NONE, "Home Assistant is up to date.")
    }
}