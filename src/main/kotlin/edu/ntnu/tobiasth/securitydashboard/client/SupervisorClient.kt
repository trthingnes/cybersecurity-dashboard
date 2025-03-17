package edu.ntnu.tobiasth.securitydashboard.client

import edu.ntnu.tobiasth.securitydashboard.client.dto.SupervisorCoreInfo
import edu.ntnu.tobiasth.securitydashboard.client.dto.SupervisorResult
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient


@RegisterRestClient(configKey = "supervisor-api")
interface SupervisorClient {
    @GET
    @Path("/core/info")
    fun getCoreInfo(): SupervisorResult<SupervisorCoreInfo>
}