package edu.ntnu.tobiasth.securitydashboard.check

import edu.ntnu.tobiasth.securitydashboard.service.dto.CheckResult
import edu.ntnu.tobiasth.securitydashboard.service.dto.Risk
import edu.ntnu.tobiasth.securitydashboard.client.dto.SupervisorAvailableUpdates
import edu.ntnu.tobiasth.securitydashboard.service.HomeAssistantService
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class UpdateCheck(
    val haService: HomeAssistantService
): Check {
    override val name: String
        get() = "System Updates"
    override val description: String
        get() = "Home Assistant components and add-ons are up-to-date."

    override fun run(): CheckResult {
        val updates = haService.getAvailableUpdates()

        if (updates.isEmpty()) return result(Risk.LOW, "Home Assistant is up-to-date.")

        return result(
            Risk.MODERATE, "System updates are available: ${
            updates.joinToString {
                when (it.updateType) {
                    SupervisorAvailableUpdates.UpdateType.OS -> "Home Assistant OS (${it.versionLatest})"
                    SupervisorAvailableUpdates.UpdateType.SUPERVISOR -> "Home Assistant Supervisor (${it.versionLatest})"
                    SupervisorAvailableUpdates.UpdateType.CORE -> "Home Assistant Core (${it.versionLatest})"
                    SupervisorAvailableUpdates.UpdateType.ADDON -> "${it.name} (${it.versionLatest})"
                }
            }
        }")
    }
}