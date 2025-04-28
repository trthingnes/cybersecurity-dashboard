package edu.ntnu.tobiasth.securitydashboard.check.impl

import edu.ntnu.tobiasth.securitydashboard.check.Check
import edu.ntnu.tobiasth.securitydashboard.check.Risk
import edu.ntnu.tobiasth.securitydashboard.service.HomeAssistantService
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class HacsCheck(
    private val haService: HomeAssistantService
) : Check() {
    override val id = "hacs-check"
    override val name = "Home Assistant Community Store"
    override val description = "Home Assistant Community Store (HACS) provides custom components that receive less scrutiny than official components. It is important to understand the potential cybersecurity and stability implications of custom components."
    override val mitigation = "If you're unsure about the potential cybersecurity and stability implications of using custom components, they should be avoided. However, if you understand and accept the risk, this check can be disabled."
    override val keywords = listOf("hacs", "third-party components")

    override fun check() = if (haService.getComponentNames().contains("hacs")) {
        yield(result(Risk.LOW, "HACS is installed."))
    } else {
        yield(result(Risk.NONE, "HACS is not installed."))
    }
}