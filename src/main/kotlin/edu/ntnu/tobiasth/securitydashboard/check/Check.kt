package edu.ntnu.tobiasth.securitydashboard.check

import edu.ntnu.tobiasth.securitydashboard.check.dto.CheckResult

interface Check {
    val name: String
    val description: String

    fun run(): CheckResult
}