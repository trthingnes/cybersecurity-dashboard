package edu.ntnu.tobiasth.securitydashboard.check

import edu.ntnu.tobiasth.securitydashboard.service.dto.CheckResult
import edu.ntnu.tobiasth.securitydashboard.service.dto.Risk
import edu.ntnu.tobiasth.securitydashboard.service.HomeAssistantService
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class SupportCheck(
    val haService: HomeAssistantService
) : Check() {
    override val name = "Hardware Support"
    override val description = "Home Assistant is officially supported for this hardware configuration."

    override fun check(): CheckResult = when (haService.getSystemInfo().supported) {
        true -> result(Risk.LOW, "Hardware is officially supported.")
        false -> result(Risk.MODERATE, "Hardware is not officially supported.")
    }
}