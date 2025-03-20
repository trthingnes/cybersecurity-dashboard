package edu.ntnu.tobiasth.securitydashboard.service

import edu.ntnu.tobiasth.securitydashboard.client.CoreClient
import edu.ntnu.tobiasth.securitydashboard.client.SupervisorClient
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.rest.client.inject.RestClient

@ApplicationScoped
class HomeAssistantService(
    @RestClient val supervisor: SupervisorClient,
    @RestClient val core: CoreClient
) {
    fun getAvailableUpdates() = supervisor.getAvailableUpdates().data.availableUpdates
    fun getInfo() = supervisor.getInfo().data
    fun getAddons() = supervisor.getAddons().data.addons
    fun getSupervisorInfo() = supervisor.getSupervisorInfo().data
    fun getErrorLog() = core.getErrorLog()
}