package edu.ntnu.tobiasth.securitydashboard.check.impl

import edu.ntnu.tobiasth.securitydashboard.check.Check
import edu.ntnu.tobiasth.securitydashboard.check.Risk
import edu.ntnu.tobiasth.securitydashboard.service.HomeAssistantService
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class SupportCheck(
    val haService: HomeAssistantService
) : Check() {
    override val id = "support-check"
    override val name = "Hardware Support"
    override val description = "To ensure system security it is recommended to use officially supported hardware, since hardware-specific vulnerabilities in unsupported hardware will likely not be fixed."
    override val mitigation = "Use hardware officially supported by Home Assistant. The official installation page has up-to-date information on supported hardware."

    override fun check() = when (haService.getSystemInfo().supported) {
        true -> yield(result(Risk.NONE, "Hardware is officially supported."))
        false -> yield(result(Risk.LOW, "Hardware is not officially supported."))
    }
}