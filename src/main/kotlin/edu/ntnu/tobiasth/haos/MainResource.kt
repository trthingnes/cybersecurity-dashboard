package edu.ntnu.tobiasth.haos

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import org.eclipse.microprofile.rest.client.inject.RestClient

@Path("/api")
class MainResource {
    @RestClient
    lateinit var haosClient: HaosClient

    @RestClient
    lateinit var ipClient: IpifyClient

    @GET
    @Path("/info")
    fun getCoreInfo() = haosClient.getCoreInfo()

    @GET
    @Path("/public-ip")
    fun getPublicIP() = ipClient.getPublicIP()
}