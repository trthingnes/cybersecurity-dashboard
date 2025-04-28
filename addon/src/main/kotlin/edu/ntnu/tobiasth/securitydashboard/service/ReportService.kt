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

    val bronzeTierThreshold = 2

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
                        it.mitigation,
                        listOf()
                    )
                )
            }
        }
        results.addAll(disabledCheck.map {
            CheckResult(it.id, Risk.DISABLED, it.name, "Check is manually disabled.", it.description, it.mitigation, listOf())
        })

        val completion = 1F - results.count { it.risk.isSignificant() }.toFloat() / results.count()
        val tier = getTier(results)
        val tierAdvanceIn = getTierAdvanceIn(results, tier)

        return Report(
            Instant.now(),
            results,
            completion,
            tier,
            tierAdvanceIn,
        )
    }

    private fun getTier(results: List<CheckResult>): Report.Tier {
        return if (results.any { it.risk.isHigh() }) Report.Tier.NONE
        else if (results.count { it.risk.isSignificant() } > bronzeTierThreshold) Report.Tier.BRONZE
        else if (results.any { it.risk.isSignificant() }) Report.Tier.SILVER
        else Report.Tier.GOLD
    }

    private fun getTierAdvanceIn(results: List<CheckResult>, tier: Report.Tier) = when (tier) {
        Report.Tier.NONE -> results.count { it.risk.isHigh() }
        Report.Tier.BRONZE -> results.count { it.risk.isSignificant()} - bronzeTierThreshold
        Report.Tier.SILVER -> results.count { it.risk.isSignificant() }
        Report.Tier.GOLD -> 0
    }
}