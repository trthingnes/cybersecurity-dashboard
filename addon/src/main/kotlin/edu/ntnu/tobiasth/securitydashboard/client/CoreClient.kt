package edu.ntnu.tobiasth.securitydashboard.client

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient


@RegisterRestClient(configKey = "core-api")
interface CoreClient {
    @GET
    @Path("/components")
    fun getComponents(): List<String>
}