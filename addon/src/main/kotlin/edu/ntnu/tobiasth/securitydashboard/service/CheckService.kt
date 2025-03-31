package edu.ntnu.tobiasth.securitydashboard.service

import edu.ntnu.tobiasth.securitydashboard.check.Check
import edu.ntnu.tobiasth.securitydashboard.persistence.DisabledCheck
import edu.ntnu.tobiasth.securitydashboard.service.dto.CheckReport
import edu.ntnu.tobiasth.securitydashboard.service.dto.CheckResult
import edu.ntnu.tobiasth.securitydashboard.service.dto.Risk
import edu.ntnu.tobiasth.securitydashboard.service.dto.Tier
import io.quarkus.arc.All
import io.quarkus.logging.Log
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
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

        val results = enabledChecks.flatMapTo(mutableListOf()) {
                try {
                    it.run()
                } catch (e: Exception) {
                    Log.error("Failed during execution of '${it.id}' (${e.javaClass.simpleName})")
                    e.printStackTrace()
                    listOf(
                        CheckResult(
                            it.id,
                            Risk.UNKNOWN,
                            it.name,
                            "Unable to complete check.",
                            it.description,
                            it.mitigation
                        )
                    )
                }
            }


        results.addAll(disabledCheck.map {
            CheckResult(it.id, Risk.DISABLED, it.name, "Check is manually disabled.", it.description, it.mitigation)
        })

        val tier =
            if (results.any { it.risk == Risk.HIGH }) Tier.BRONZE
            else if (results.any { it.risk == Risk.MODERATE }) Tier.SILVER
            else Tier.GOLD
        val completion: Pair<Float, Int> = when (tier) {
            Tier.BRONZE -> {
                val completed = results.count { it.risk == Risk.LOW }
                val required = completed + results.count { it.risk == Risk.HIGH }
                Pair(completed / required.toFloat(), required - completed)
            }
            Tier.SILVER -> {
                val completed = results.count { it.risk == Risk.LOW }
                val required = completed + results.count { it.risk == Risk.MODERATE }
                Pair(completed / required.toFloat(), required - completed)
            }
            Tier.GOLD -> Pair(1.0F, 0)
        }

        return CheckReport(
            Instant.now(),
            results,
            tier,
            completion.first,
            completion.second
        )
    }
}