package edu.ntnu.tobiasth.securitydashboard.client

import jakarta.ws.rs.GET
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient(configKey = "ipify-api")
interface IpifyClient {
    @GET
    fun getPublicIP(): String
}