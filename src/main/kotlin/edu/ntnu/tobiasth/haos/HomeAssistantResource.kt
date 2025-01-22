package edu.ntnu.tobiasth.haos

import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

@Path("/homeassistant")
class HomeAssistantResource {
    @Inject
    lateinit var client: HaosClient

    @GET
    @Path("/core/info")
    fun getCoreInfo() = client.getCoreInfo()
}