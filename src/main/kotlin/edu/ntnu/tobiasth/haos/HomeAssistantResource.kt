package edu.ntnu.tobiasth.haos

import io.quarkus.logging.Log
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.rest.client.inject.RestClient

@Path("/api/home-assistant")
class HomeAssistantResource {
    @RestClient
    lateinit var client: HaosClient

    @ConfigProperty(name = "app.supervisor.token")
    lateinit var supervisorToken: String

    @GET
    @Path("/core/info")
    fun getCoreInfo() = client.getCoreInfo()

    @GET
    @Path("/log-token")
    fun postLogToken(): Response {
        Log.info("Token is $supervisorToken")
        Log.info("SUPERVISOR_TOKEN is ${System.getenv("SUPERVISOR_TOKEN")}")

        return Response.noContent().build()
    }
}