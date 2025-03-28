package edu.ntnu.tobiasth.securitydashboard

import edu.ntnu.tobiasth.securitydashboard.persistence.DisabledCheck
import edu.ntnu.tobiasth.securitydashboard.service.CheckService
import io.quarkus.logging.Log
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam

@Path("/api")
class MainResource {
    @Inject
    lateinit var checkService: CheckService

    @GET
    @Path("/report")
    fun getReport() = checkService.run()

    @POST
    @Path("/checks/{id}/enable")
    @Transactional
    fun postChecksEnable(@PathParam("id") id: String) = DisabledCheck.delete("checkId", id)

    @POST
    @Path("/checks/{id}/disable")
    @Transactional
    fun postChecksDisable(@PathParam("id") id: String) = if (DisabledCheck.list("checkId", id).isNotEmpty()) {
        Log.debug("Ignoring disable request since check '$id' is already disabled.")
    } else {
        DisabledCheck(id).persist()
    }

}