package edu.ntnu.tobiasth.securitydashboard.check.impl

import edu.ntnu.tobiasth.securitydashboard.check.Check
import edu.ntnu.tobiasth.securitydashboard.check.dto.CheckResult
import edu.ntnu.tobiasth.securitydashboard.check.dto.Risk
import edu.ntnu.tobiasth.securitydashboard.service.IpService
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class ProxyCheck(
    val ipService: IpService
) : Check {
    override val name: String
        get() = "Remote Access Check"
    override val description: String
        get() = "Check Home Assistant is behind a secure proxy"

    override fun run(): CheckResult {
        return CheckResult(
            this,
            Risk.MODERATE,
            "Public IP: ${ipService.getPublicIP()}"
        )
    }
}