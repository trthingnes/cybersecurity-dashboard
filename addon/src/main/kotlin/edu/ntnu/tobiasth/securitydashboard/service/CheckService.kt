package edu.ntnu.tobiasth.securitydashboard.service

import edu.ntnu.tobiasth.securitydashboard.check.Check
import edu.ntnu.tobiasth.securitydashboard.service.dto.CheckReport
import edu.ntnu.tobiasth.securitydashboard.service.dto.CheckResult
import edu.ntnu.tobiasth.securitydashboard.service.dto.Risk
import io.quarkus.arc.All
import io.quarkus.logging.Log
import io.quarkus.runtime.Startup
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

    @Startup
    fun run() {
        Log.info("Running ${checks.size} checks...")

        val results = checks.flatMap {
            try {
                it.run()
            } catch (e: Exception) {
                e.printStackTrace()
                listOf(CheckResult(Risk.UNKNOWN, it.name, "Unable to complete check.", it.description, it.mitigation))
            }
        }

        report = CheckReport(
            Instant.now(),
            results
        )
    }
}