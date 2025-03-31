package edu.ntnu.tobiasth.securitydashboard.check

import edu.ntnu.tobiasth.securitydashboard.service.dto.CheckResult
import edu.ntnu.tobiasth.securitydashboard.service.dto.Risk

abstract class Check {
    abstract val id: String
    abstract val name: String
    abstract val description: String
    abstract val mitigation: String
    private val results: MutableList<CheckResult> = mutableListOf()

    fun run(): List<CheckResult> {
        results.clear()
        check()
        return results
    }

    protected abstract fun check()
    protected fun result(title: String, risk: Risk, message: String) = CheckResult(id, risk, title, message, description, mitigation)
    protected fun result(risk: Risk, message: String) = result(name, risk, message)
    protected fun yield(result: CheckResult) {
        results.add(result)
    }
}