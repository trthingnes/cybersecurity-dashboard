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
    override val description = "Home Assistant provides official support for a wide range of hardware. To ensure the security of the instance it is recommended to use supported hardware, since hardware specific vulnerabilities in unsupported hardware will likely not get fixed."
    override val mitigation = "To be sure that hardware specific vulnerabilities will be patched, consider using hardware that is supported by Home Assistant. The installation page has more up-to-date information on supported hardware."

    override fun check() = when (haService.getSystemInfo().supported) {
        true -> yield(result(Risk.LOW, "Hardware is officially supported."))
        false -> yield(result(Risk.MODERATE, "Hardware is not officially supported."))
    }
}