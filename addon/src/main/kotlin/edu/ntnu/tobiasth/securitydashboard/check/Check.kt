package edu.ntnu.tobiasth.securitydashboard.check

abstract class Check {
    abstract val id: String
    abstract val name: String
    abstract val description: String
    abstract val mitigation: String
    abstract val keywords: List<String>
    private val results: MutableList<CheckResult> = mutableListOf()

    fun run(): List<CheckResult> {
        results.clear()
        check()
        return results
    }

    protected abstract fun check()
    protected fun result(title: String, risk: Risk, message: String) = CheckResult(id, risk, title, message, description, mitigation, keywords)
    protected fun result(risk: Risk, message: String) = result(name, risk, message)
    protected fun yield(result: CheckResult) {
        results.add(result)
    }
}