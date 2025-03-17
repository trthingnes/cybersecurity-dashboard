package edu.ntnu.tobiasth.securitydashboard.check.dto

import edu.ntnu.tobiasth.securitydashboard.check.Check
data class CheckResult(
    val check: Check,
    val risk: Risk,
    val message: String?
)
