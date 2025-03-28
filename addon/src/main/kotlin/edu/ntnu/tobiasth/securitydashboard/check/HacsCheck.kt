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
    override val description = "Home Assistant Community Store (HACS) provides custom components that are not kept to the same standard as the ones bundles with Home Assistant. Because of this it is important to understand the implications of using it."
    override val mitigation = "If you are not sure what the implications of using custom components are, it's recommended to avoid HACS since custom components are not reviewed as thoroughly as Home Assistant components. However, if you understand the implications, you can disable this check."

    override suspend fun check() = if (homeassistantService.getComponentNames().contains("hacs")) {
        yield(result(Risk.MODERATE, "HACS is installed."))
    } else {
        yield(result(Risk.LOW, "HACS is not installed."))
    }
}