package edu.ntnu.tobiasth.securitydashboard.check.dto

data class CheckResult(
    val name: String,
    val description: String,
    val risk: Risk,
    val message: String?
)
