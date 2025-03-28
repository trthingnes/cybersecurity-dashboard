package edu.ntnu.tobiasth.securitydashboard.check

import edu.ntnu.tobiasth.securitydashboard.service.HomeAssistantService
import edu.ntnu.tobiasth.securitydashboard.service.dto.Risk
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class HacsCheck(
    private val homeassistantService: HomeAssistantService
) : Check() {
    override val id = "hacs-check"
    override val name = "Home Assistant Community Store"
    override val description = "Home Assistant Community Store (HACS) provides custom components that have not been reviewed to the same standard as components bundles with Home Assistant. This means that it is very important to understand the potential implications of downloading custom components before doing so."
    override val mitigation = "If you're unsure about the potential cybersecurity and stability implications of using custom components, it's recommended to avoid components from HACS altogether. However, if you understand and accept the risk, this check can be disabled."

    override suspend fun check() = if (homeassistantService.getComponentNames().contains("hacs")) {
        yield(result(Risk.MODERATE, "HACS is installed."))
    } else {
        yield(result(Risk.LOW, "HACS is not installed."))
    }
}