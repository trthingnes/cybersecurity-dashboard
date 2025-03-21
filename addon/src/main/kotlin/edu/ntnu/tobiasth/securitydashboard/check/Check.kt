package edu.ntnu.tobiasth.securitydashboard.check

import edu.ntnu.tobiasth.securitydashboard.service.dto.CheckResult
import edu.ntnu.tobiasth.securitydashboard.service.dto.Risk

abstract class Check {
    abstract val name: String
    abstract val description: String
    private val results: MutableList<CheckResult> = mutableListOf()

    fun run(): List<CheckResult> {
        results.clear()
        check()?.let { results.add(it) }
        return results
    }

    protected abstract fun check(): CheckResult?
    protected fun result(name: String, risk: Risk, message: String?) = CheckResult(name, description, risk, message)
    protected fun result(risk: Risk, message: String?) = CheckResult(name, description, risk, message)
    protected fun yield(result: CheckResult) {
        results.add(result)
    }
}