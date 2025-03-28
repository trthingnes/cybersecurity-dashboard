package edu.ntnu.tobiasth.securitydashboard.check

import edu.ntnu.tobiasth.securitydashboard.service.dto.CheckResult
import edu.ntnu.tobiasth.securitydashboard.service.dto.Risk
import edu.ntnu.tobiasth.securitydashboard.client.dto.supervisor.AvailableUpdates
import edu.ntnu.tobiasth.securitydashboard.service.HomeAssistantService
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class UpdateCheck(
    val haService: HomeAssistantService
) : Check() {
    override val id = "update-check"
    override val name = "Software Updates"
    override val description = "Home Assistant components and add-ons are up-to-date."
    override val mitigation = "Ensure that Home Assistant and its components are up-to-date to ensure your system is avoiding any known vulnerabilities."

    override suspend fun check() {
        val updates = haService.getAvailableUpdates()
        if (updates.isEmpty()) {
            yield(result(Risk.LOW, "Home Assistant is up-to-date."))
        } else {
            yield(
                result(
                    Risk.MODERATE, "System updates are available: ${
                    updates.joinToString {
                        when (it.updateType) {
                            AvailableUpdates.UpdateType.OS -> "Home Assistant OS (${it.versionLatest})"
                            AvailableUpdates.UpdateType.SUPERVISOR -> "Home Assistant Supervisor (${it.versionLatest})"
                            AvailableUpdates.UpdateType.CORE -> "Home Assistant Core (${it.versionLatest})"
                            AvailableUpdates.UpdateType.ADDON -> "${it.name} (${it.versionLatest})"
                        }
                    }
                }"))
        }
    }
}