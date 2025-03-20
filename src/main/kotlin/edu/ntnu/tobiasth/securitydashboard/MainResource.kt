package edu.ntnu.tobiasth.securitydashboard

import edu.ntnu.tobiasth.securitydashboard.service.CheckService
import edu.ntnu.tobiasth.securitydashboard.service.dto.CheckReport
import io.quarkus.logging.Log
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path

@Path("/api")
class MainResource {
    @Inject
    lateinit var runner: CheckService

    @GET
    @Path("/report")
    fun getReport(): CheckReport? {
        return runner.report
    }

    @POST
    @Path("/check")
    fun postCheck() {
        Log.info("Received a request to run checks.")
        runner.run()
    }
}