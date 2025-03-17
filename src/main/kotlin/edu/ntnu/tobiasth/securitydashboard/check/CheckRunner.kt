package edu.ntnu.tobiasth.securitydashboard.check

import edu.ntnu.tobiasth.securitydashboard.check.dto.CheckReport
import io.quarkus.arc.All
import io.quarkus.logging.Log
import io.quarkus.scheduler.Scheduled
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import java.time.Instant

@ApplicationScoped
class CheckRunner {
    @Inject
    @All
    @Suppress("CdiInjectionPointsInspection")
    private lateinit var checks: MutableList<Check>

    var report: CheckReport? = null

    @Scheduled(every = "6h")
    fun run() {
        Log.info("Running checks.")
        val results = checks.map { it.run() }

        Log.info("Generating report.")
        report = CheckReport(
            Instant.now(),
            results
        )
    }
}