package edu.ntnu.tobiasth.securitydashboard.service

import edu.ntnu.tobiasth.securitydashboard.check.Check
import edu.ntnu.tobiasth.securitydashboard.persistence.DisabledCheck
import edu.ntnu.tobiasth.securitydashboard.service.dto.CheckReport
import edu.ntnu.tobiasth.securitydashboard.service.dto.CheckResult
import edu.ntnu.tobiasth.securitydashboard.service.dto.Risk
import io.quarkus.arc.All
import io.quarkus.logging.Log
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import java.time.Instant

@ApplicationScoped
class CheckService {
    @Inject
    @All
    @Suppress("CdiInjectionPointsInspection")
    private lateinit var checks: MutableList<Check>

    fun run(): CheckReport {
        val disabledCheckIds = DisabledCheck.listAll().map { it.checkId }
        val enabledChecks = checks.filter { !disabledCheckIds.contains(it.id) }
        val disabledCheck = checks.filter { disabledCheckIds.contains(it.id) }

        Log.info("Running ${enabledChecks.size}/${checks.size} checks...")

        val results = runBlocking {
            enabledChecks.associateWith { check ->
                async { check.run() } // Associate every check with a Kotlin Coroutine running it.
            }.flatMapTo(mutableListOf()) { (check, coroutine) ->
                try {
                    coroutine.await()
                } catch (e: Exception) {
                    e.printStackTrace()
                    listOf(
                        CheckResult(
                            check.id,
                            Risk.UNKNOWN,
                            check.name,
                            "Unable to complete check.",
                            check.description,
                            check.mitigation
                        )
                    )
                }
            }
        }

        results.addAll(disabledCheck.map {
            CheckResult(it.id, Risk.DISABLED, it.name, "Check is manually disabled.", it.description, it.mitigation)
        })

        return CheckReport(
            Instant.now(),
            results
        )
    }
}