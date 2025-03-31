package edu.ntnu.tobiasth.securitydashboard.check

import edu.ntnu.tobiasth.securitydashboard.service.dto.Risk
import edu.ntnu.tobiasth.securitydashboard.service.HomeAssistantService
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class SupportCheck(
    val haService: HomeAssistantService
) : Check() {
    override val id = "support-check"
    override val name = "Hardware Support"
    override val description = "Home Assistant is officially supported for this hardware configuration."
    override val mitigation = "Ensure that your hardware is supported by Home Assistant by visiting the installation page."

    override fun check() = when (haService.getSystemInfo().supported) {
        true -> yield(result(Risk.LOW, "Hardware is officially supported."))
        false -> yield(result(Risk.MODERATE, "Hardware is not officially supported."))
    }
}