package edu.ntnu.tobiasth.haos

import edu.ntnu.tobiasth.haos.dto.CoreInfoDto
import io.quarkus.arc.profile.IfBuildProfile
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@IfBuildProfile("prod")
@RegisterRestClient(configKey = "haos-api")
interface HaosClient {
    @GET
    @Path("/core/info")
    fun getCoreInfo(): CoreInfoDto
}