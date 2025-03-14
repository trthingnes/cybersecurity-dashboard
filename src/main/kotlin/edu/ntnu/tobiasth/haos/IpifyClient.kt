package edu.ntnu.tobiasth.haos

import jakarta.ws.rs.GET
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient(configKey = "ipify-api")
interface IpifyClient {
    @GET
    fun getPublicIP(): String
}