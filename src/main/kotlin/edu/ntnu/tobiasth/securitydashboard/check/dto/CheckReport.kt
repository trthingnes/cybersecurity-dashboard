package edu.ntnu.tobiasth.securitydashboard.check.dto

import java.time.Instant

data class CheckReport(
    val timestamp: Instant,
    val results: List<CheckResult>
)
