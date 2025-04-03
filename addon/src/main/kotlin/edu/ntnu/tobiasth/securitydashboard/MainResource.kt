package edu.ntnu.tobiasth.securitydashboard

import edu.ntnu.tobiasth.securitydashboard.persistence.DisabledCheck
import edu.ntnu.tobiasth.securitydashboard.service.HomeAssistantService
import edu.ntnu.tobiasth.securitydashboard.service.ReportService
import edu.ntnu.tobiasth.securitydashboard.service.dto.CheckReport
import edu.ntnu.tobiasth.securitydashboard.service.dto.Risk
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
    private val reportService: ReportService,
    private val haService: HomeAssistantService
) {
    private val reports: LimitedSizeList<CheckReport> = LimitedSizeList(2)

    @Scheduled(every="{OPTION_SCHEDULE_INTERVAL}")
    fun scheduledReportGenerate() {
        Log.info("Generating report according to schedule...")
        val newReport = reportService.generate()

        // If an older report exists, compare it to the new one and notify the user if relevant.
        if (reports.isNotEmpty()) {
            val oldReport = reports[reports.size - 1]
            val newMediumHighRisks = newReport.results.filter { listOf(Risk.HIGH, Risk.MODERATE).contains(it.risk) && !oldReport.results.contains(it)}
            if (newMediumHighRisks.isNotEmpty()) {
                haService.createNotification("The latest scheduled report discovered ${newMediumHighRisks.size} new moderate or high risk${if (newMediumHighRisks.size > 1) "s" else ""}. Visit the Cybersecurity Dashboard to keep Home Assistant secure.")
            }
        }

        reports.add(newReport)
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
    @Path("/check/{id}/enable")
    @Transactional
    fun postChecksEnable(@PathParam("id") id: String) = DisabledCheck.delete("checkId", id)

    @POST
    @Path("/check/{id}/disable")
    @Transactional
    fun postChecksDisable(@PathParam("id") id: String) = if (DisabledCheck.list("checkId", id).isNotEmpty()) {
        Log.debug("Ignoring disable request since check '$id' is already disabled.")
    } else {
        DisabledCheck(id).persist()
    }
}