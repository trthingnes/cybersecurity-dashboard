package edu.ntnu.tobiasth.haos

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import org.eclipse.microprofile.rest.client.inject.RestClient

@Path("/homeassistant")
class HomeAssistantResource {
    @RestClient
    lateinit var client: HaosClient

    @GET
    @Path("/core/info")
    fun getCoreInfo() = client.getCoreInfo()
}