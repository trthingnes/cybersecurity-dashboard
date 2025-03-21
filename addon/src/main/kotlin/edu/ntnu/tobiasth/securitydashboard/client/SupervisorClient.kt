package edu.ntnu.tobiasth.securitydashboard.client

import edu.ntnu.tobiasth.securitydashboard.client.dto.supervisor.*
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient


@RegisterRestClient(configKey = "supervisor-api")
interface SupervisorClient {
    @GET
    @Path("/info")
    fun getInfo(): Result<Info>

    @GET
    @Path("/addons")
    fun getAddons(): Result<Addons>

    @GET
    @Path("/available_updates")
    fun getAvailableUpdates(): Result<AvailableUpdates>

    @GET
    @Path("/core/info")
    fun getCoreInfo(): Result<CoreInfo>

    @GET
    @Path("/supervisor/info")
    fun getSupervisorInfo(): Result<SupervisorInfo>
}