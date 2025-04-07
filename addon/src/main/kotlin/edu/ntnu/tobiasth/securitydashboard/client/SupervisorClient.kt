package edu.ntnu.tobiasth.securitydashboard.client

import edu.ntnu.tobiasth.securitydashboard.client.dto.supervisor.*
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
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
    @Path("/addons/{slug}/logs")
    @Produces("text/x-log")
    fun getAddonLogs(@PathParam("slug") slug: String): String

    @GET
    @Path("/store/addons")
    fun getStoreAddons(): Result<Addons>

    @GET
    @Path("/store/repositories")
    fun getStoreRepositories(): Result<List<StoreRepository>>

    @GET
    @Path("/available_updates")
    fun getAvailableUpdates(): Result<AvailableUpdates>

    @GET
    @Path("/core/info")
    fun getCoreInfo(): Result<CoreInfo>

    @GET
    @Path("/core/logs")
    @Produces("text/x-log")
    fun getCoreLogs(): String

    @GET
    @Path("/supervisor/info")
    fun getSupervisorInfo(): Result<SupervisorInfo>

    @GET
    @Path("/supervisor/logs")
    @Produces("text/x-log")
    fun getSupervisorLogs(): String
}