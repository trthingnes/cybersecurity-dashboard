package edu.ntnu.tobiasth.securitydashboard.check.impl

import edu.ntnu.tobiasth.securitydashboard.check.Check
import edu.ntnu.tobiasth.securitydashboard.check.Risk
import edu.ntnu.tobiasth.securitydashboard.client.dto.supervisor.AvailableUpdates
import edu.ntnu.tobiasth.securitydashboard.service.HomeAssistantService
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class UpdateCheck(
    val haService: HomeAssistantService
) : Check() {
    override val id = "update-check"
    override val name = "Software Updates"
    override val description = "Ensuring Home Assistant components and add-ons are up-to-date is a simple way to mitigate risks to the system."
    override val mitigation = "Use the built-in update service to update the listed components."

    override fun check() {
        val updates = haService.getAvailableUpdates()
        if (updates.isEmpty()) {
            yield(result(Risk.NONE, "Home Assistant is up-to-date."))
        } else {
            yield(
                result(
                    Risk.LOW, "System updates are available: ${
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