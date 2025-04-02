package edu.ntnu.tobiasth.securitydashboard.service

import edu.ntnu.tobiasth.securitydashboard.client.CoreClient
import edu.ntnu.tobiasth.securitydashboard.client.SupervisorClient
import edu.ntnu.tobiasth.securitydashboard.client.dto.core.Notification
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.rest.client.inject.RestClient

@ApplicationScoped
class HomeAssistantService(
    @RestClient val supervisor: SupervisorClient,
    @RestClient val core: CoreClient
) {
    fun getAvailableUpdates() = supervisor.getAvailableUpdates().data.availableUpdates
    fun getSystemInfo() = supervisor.getInfo().data
    fun getCoreInfo() = supervisor.getCoreInfo().data
    fun getSupervisorInfo() = supervisor.getSupervisorInfo().data
    fun getInstalledAddons() = supervisor.getAddons().data.addons
    fun getAddonRepositories() = supervisor.getStoreRepositories().data
    fun getComponentNames() = core.getComponents()
    fun createNotification(title: String?, message: String) = core.createNotification(Notification(title, message))
}