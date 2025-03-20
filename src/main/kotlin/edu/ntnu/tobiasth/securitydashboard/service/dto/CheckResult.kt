package edu.ntnu.tobiasth.securitydashboard.service.dto

data class CheckResult(
    val name: String,
    val description: String,
    val risk: Risk,
    val message: String?
)
