package edu.ntnu.tobiasth.securitydashboard

import edu.ntnu.tobiasth.securitydashboard.service.HomeAssistantService
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

@Path("/api/devices")
class DevicesResource(
    val haService: HomeAssistantService
) {
    @GET
    fun get() = haService.getDeviceStates()
}