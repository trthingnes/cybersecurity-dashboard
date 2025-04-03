package edu.ntnu.tobiasth.securitydashboard.service

import edu.ntnu.tobiasth.securitydashboard.check.Check
import edu.ntnu.tobiasth.securitydashboard.persistence.DisabledCheck
import edu.ntnu.tobiasth.securitydashboard.persistence.Report
import edu.ntnu.tobiasth.securitydashboard.check.CheckResult
import edu.ntnu.tobiasth.securitydashboard.check.Risk
import io.quarkus.arc.All
import io.quarkus.logging.Log
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import java.time.Instant

@ApplicationScoped
class ReportService {
    @Inject
    @All
    @Suppress("CdiInjectionPointsInspection")
    private lateinit var checks: MutableList<Check>

    fun generate(): Report {
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

        val tier = getTier(results)
        val completion = getTierCompletion(tier, results)

        return Report(
            Instant.now(),
            results,
            tier,
            completion.first,
            completion.second
        )
    }

    fun getTier(results: List<CheckResult>): Report.Tier {
        return if (results.any { it.risk == Risk.HIGH }) Report.Tier.BRONZE
        else if (results.any { it.risk == Risk.MODERATE }) Report.Tier.SILVER
        else Report.Tier.GOLD
    }

    fun getTierCompletion(tier: Report.Tier, results: List<CheckResult>) = when (tier) {
        Report.Tier.BRONZE -> {
            val completed = results.count { it.risk == Risk.LOW }
            val required = completed + results.count { it.risk == Risk.HIGH }
            Pair(completed / required.toFloat(), required - completed)
        }

        Report.Tier.SILVER -> {
            val completed = results.count { it.risk == Risk.LOW }
            val required = completed + results.count { it.risk == Risk.MODERATE }
            Pair(completed / required.toFloat(), required - completed)
        }

        Report.Tier.GOLD -> Pair(1.0F, 0)
    }
}