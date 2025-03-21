package edu.ntnu.tobiasth.securitydashboard.client

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient(baseUri = "https://cloudflare.com/cdn-cgi")
interface CloudflareClient {
    @GET
    @Path("/trace")
    fun getTrace(): String
}