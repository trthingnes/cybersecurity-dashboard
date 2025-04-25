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
    override val description = "Home Assistant Community Store (HACS) provides custom components that have not been reviewed to the same standard as components bundles with Home Assistant. This means that it is very important to understand the potential implications of downloading custom components before doing so."
    override val mitigation = "If you're unsure about the potential cybersecurity and stability implications of using custom components, it's recommended to avoid components from HACS altogether. However, if you understand and accept the risk, this check can be disabled."

    override fun check() = if (haService.getComponentNames().contains("hacs")) {
        yield(result(Risk.LOW, "HACS is installed."))
    } else {
        yield(result(Risk.NONE, "HACS is not installed."))
    }
}