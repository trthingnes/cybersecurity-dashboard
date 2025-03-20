package edu.ntnu.tobiasth.securitydashboard.check

import edu.ntnu.tobiasth.securitydashboard.check.dto.CheckReport
import edu.ntnu.tobiasth.securitydashboard.check.dto.CheckResult
import edu.ntnu.tobiasth.securitydashboard.check.dto.Risk

interface Check {
    val name: String
    val description: String

    fun run(): CheckResult
    fun result(risk: Risk, message: String?) = CheckResult(name, description, risk, message)
}