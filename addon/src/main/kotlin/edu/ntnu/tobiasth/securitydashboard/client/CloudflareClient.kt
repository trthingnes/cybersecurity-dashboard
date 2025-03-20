package edu.ntnu.tobiasth.securitydashboard.client

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient(configKey = "cf-cdn-api")
interface CloudflareClient {
    @GET
    @Path("/trace")
    fun getTrace(): String
}