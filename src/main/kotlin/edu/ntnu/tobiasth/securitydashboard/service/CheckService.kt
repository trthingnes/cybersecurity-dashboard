package edu.ntnu.tobiasth.securitydashboard.service

import edu.ntnu.tobiasth.securitydashboard.check.Check
import edu.ntnu.tobiasth.securitydashboard.service.dto.CheckReport
import io.quarkus.arc.All
import io.quarkus.logging.Log
import io.quarkus.scheduler.Scheduled
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import java.time.Instant

@ApplicationScoped
class CheckService {
    @Inject
    @All
    @Suppress("CdiInjectionPointsInspection")
    private lateinit var checks: MutableList<Check>

    var report: CheckReport? = null

    @Scheduled(every = "6h")
    fun run() {
        Log.info("Running ${checks.size} checks...")

        val results = checks.map { it.run() }
        report = CheckReport(
            Instant.now(),
            results
        )
    }
}