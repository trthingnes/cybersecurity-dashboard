package edu.ntnu.tobiasth.haos

import edu.ntnu.tobiasth.haos.dto.CoreInfoDto
import edu.ntnu.tobiasth.haos.dto.ResultDto
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient(configKey = "haos-api")
interface HaosClient {
    @GET
    @Path("/core/info")
    fun getCoreInfo(): ResultDto<CoreInfoDto>
}