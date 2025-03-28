package edu.ntnu.tobiasth.securitydashboard.service.dto

data class CheckResult(
    val id: String,
    val risk: Risk,
    val title: String,
    val summary: String,
    val description: String,
    val mitigation: String,
)
