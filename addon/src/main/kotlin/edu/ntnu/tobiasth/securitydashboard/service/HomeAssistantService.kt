package edu.ntnu.tobiasth.securitydashboard.service

import edu.ntnu.tobiasth.securitydashboard.client.CoreClient
import edu.ntnu.tobiasth.securitydashboard.client.SupervisorClient
import edu.ntnu.tobiasth.securitydashboard.client.dto.core.Notification
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.rest.client.inject.RestClient

val DEVICE_PREFIXES = arrayOf(
    "alarm_control_panel",
    "camera",
    "climate",
    "cover",
    "fan",
    "humidifier",
    "light",
    "lock",
    "media_player",
    "remote",
    "siren",
    "vacuum",
    "valve",
    "water_heater"
)

@ApplicationScoped
class HomeAssistantService(
    @RestClient val supervisor: SupervisorClient,
    @RestClient val core: CoreClient
) {
    fun getAvailableUpdates() = supervisor.getAvailableUpdates().data.availableUpdates
    fun getSystemInfo() = supervisor.getInfo().data
    fun getCoreInfo() = supervisor.getCoreInfo().data
    fun getCoreLogs() = supervisor.getCoreLogs().split("\n")
    fun getSupervisorInfo() = supervisor.getSupervisorInfo().data
    fun getSupervisorLogs() = supervisor.getSupervisorLogs().split("\n")
    fun getAddonRepositories() = supervisor.getStoreRepositories().data
    fun getInstalledAddons() = supervisor.getAddons().data.addons
    fun getAddonLogs() = supervisor.getAddons().data.addons.associateTo(mutableMapOf()) {
        Pair(
            it.name,
            supervisor.getAddonLogs(it.slug).split("\n")
        )
    }

    fun getComponentNames() = core.getComponents()
    fun getDeviceStates() = core.getStates().filter { DEVICE_PREFIXES.any { prefix -> it.entityId.startsWith(prefix) } }
    fun createNotification(message: String, title: String = "Cybersecurity Dashboard") =
        core.createNotification(Notification(title, message))
}