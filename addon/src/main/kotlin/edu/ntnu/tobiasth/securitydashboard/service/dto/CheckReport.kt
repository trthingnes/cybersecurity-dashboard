package edu.ntnu.tobiasth.securitydashboard.service.dto

import java.time.Instant

data class CheckReport(
    val timestamp: Instant,
    val results: List<CheckResult>,
    val tier: Tier,
    val tierCompletion: Float,
    val tierAdvanceIn: Int
)
