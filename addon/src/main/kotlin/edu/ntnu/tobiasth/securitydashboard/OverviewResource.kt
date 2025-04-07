package edu.ntnu.tobiasth.securitydashboard

import edu.ntnu.tobiasth.securitydashboard.persistence.DisabledCheck
import edu.ntnu.tobiasth.securitydashboard.service.HomeAssistantService
import edu.ntnu.tobiasth.securitydashboard.service.ReportService
import edu.ntnu.tobiasth.securitydashboard.persistence.Report
import edu.ntnu.tobiasth.securitydashboard.check.Risk
import io.quarkus.logging.Log
import io.quarkus.panache.common.Page
import io.quarkus.scheduler.Scheduled
import jakarta.transaction.Transactional
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam

@Path("/api/overview")
class OverviewResource(
    private val reportService: ReportService,
    private val haService: HomeAssistantService
) {
    @Scheduled(every="{OPTION_SCHEDULE_INTERVAL}")
    @Transactional
    fun scheduledReportGenerate() {
        val maxReportCount = 5
        val reportCount = Report.count()
        if (reportCount > maxReportCount) {
            Log.info("Deleting ${reportCount - maxReportCount} old report(s)...")
            val reportsToKeep = Report.find("order by timestamp desc").page(Page.ofSize(maxReportCount)).list()
            Report.delete("timestamp < ?1", reportsToKeep.last().timestamp)
        }

        Log.info("Generating report according to schedule...")
        val newReport = reportService.generate()
        val latestOldReport = findLatestOldReport()

        if (latestOldReport != null) {
            Log.debug("Timestamps of old reports: ${Report.listAll().map { it.timestamp }.joinToString()}")
            Log.debug("Latest old report has timestamp: ${latestOldReport.timestamp}")
            val newMediumHighRisks = newReport.results.filter { listOf(Risk.HIGH, Risk.MODERATE).contains(it.risk) && !latestOldReport.results.contains(it)}
            if (newMediumHighRisks.isNotEmpty()) {
                Log.info("Notifying user about new risks...")
                haService.createNotification("The latest scheduled report discovered ${newMediumHighRisks.size} new moderate or high risk${if (newMediumHighRisks.size > 1) "s" else ""}. Visit the Cybersecurity Dashboard to keep Home Assistant secure.")
            }
        }

        newReport.persistAndFlush()
    }

    @GET
    @Transactional
    fun get(): Report {
        val latestOldReport = findLatestOldReport()
        if (latestOldReport == null) {
            Log.info("Generating report since none are available...")
            val newReport = reportService.generate()
            newReport.persistAndFlush()
            return newReport
        }
        Log.debug("Returning existing report...")
        return latestOldReport
    }

    @POST
    @Path("/generate")
    @Transactional
    fun postGenerate() {
        Log.info("Generating report since a new one was requested...")
        reportService.generate().persistAndFlush()
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

    fun findLatestOldReport() = Report.find("order by timestamp desc").firstResult()
}