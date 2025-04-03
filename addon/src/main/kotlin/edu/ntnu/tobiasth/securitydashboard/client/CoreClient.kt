package edu.ntnu.tobiasth.securitydashboard.client

import edu.ntnu.tobiasth.securitydashboard.client.dto.core.Notification
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient


@RegisterRestClient(configKey = "core-api")
interface CoreClient {
    @GET
    @Path("/components")
    fun getComponents(): List<String>

    @POST
    @Path("/services/persistent_notification/create")
    fun createNotification(@RequestBody notification: Notification)
}