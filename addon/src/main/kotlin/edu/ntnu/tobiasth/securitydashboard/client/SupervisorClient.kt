package edu.ntnu.tobiasth.securitydashboard.client

import edu.ntnu.tobiasth.securitydashboard.client.dto.*
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient


@RegisterRestClient(configKey = "supervisor-api")
interface SupervisorClient {
    @GET
    @Path("/info")
    fun getInfo(): SupervisorResult<SupervisorInfo>

    @GET
    @Path("/addons")
    fun getAddons(): SupervisorResult<SupervisorAddons>

    @GET
    @Path("/available_updates")
    fun getAvailableUpdates(): SupervisorResult<SupervisorAvailableUpdates>

    @GET
    @Path("/core/info")
    fun getCoreInfo(): SupervisorResult<SupervisorCoreInfo>

    @GET
    @Path("/supervisor/info")
    fun getSupervisorInfo(): SupervisorResult<SupervisorSupervisorInfo>
}