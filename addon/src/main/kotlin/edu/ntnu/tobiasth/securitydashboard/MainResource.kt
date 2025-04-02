package edu.ntnu.tobiasth.securitydashboard

import edu.ntnu.tobiasth.securitydashboard.persistence.DisabledCheck
import edu.ntnu.tobiasth.securitydashboard.service.ReportService
import edu.ntnu.tobiasth.securitydashboard.service.HomeAssistantService
import edu.ntnu.tobiasth.securitydashboard.service.OptionsService
import edu.ntnu.tobiasth.securitydashboard.service.dto.CheckReport
import edu.ntnu.tobiasth.securitydashboard.util.LimitedSizeList
import io.quarkus.logging.Log
import io.quarkus.scheduler.Scheduled
import jakarta.transaction.Transactional
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam

@Path("/api")
class MainResource(
    private val reportService: ReportService
) {
    private val reports: LimitedSizeList<CheckReport> = LimitedSizeList(2)

    @Scheduled(every="{OPTION_SCHEDULE_INTERVAL}")
    fun scheduledReportGenerate() {
        Log.info("Generating report according to schedule...")
        reports.add(reportService.generate())
    }

    @GET
    @Path("/report")
    fun getReport(): CheckReport {
        if (reports.isEmpty()) {
            Log.info("Generating report since none are available...")
            reports.add(reportService.generate())
        }
        return reports[reports.size - 1]
    }

    @POST
    @Path("/report/generate")
    fun postReportGenerate() {
        Log.info("Generating report since a new one was requested...")
        reports.add(reportService.generate())
    }

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