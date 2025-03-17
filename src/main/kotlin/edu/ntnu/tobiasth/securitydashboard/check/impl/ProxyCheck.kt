package edu.ntnu.tobiasth.securitydashboard.check.impl

import edu.ntnu.tobiasth.securitydashboard.check.Check
import edu.ntnu.tobiasth.securitydashboard.check.dto.CheckResult
import edu.ntnu.tobiasth.securitydashboard.check.dto.Risk
import edu.ntnu.tobiasth.securitydashboard.client.CoreClient
import edu.ntnu.tobiasth.securitydashboard.client.IpifyClient
import io.quarkus.logging.Log
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.rest.client.inject.RestClient

@ApplicationScoped
class ProxyCheck(
    @RestClient val ipClient: IpifyClient
) : Check {
    override val name: String
        get() = "Remote Access Check"
    override val description: String
        get() = "Check Home Assistant is behind a secure proxy"

    override fun run(): CheckResult {
        return CheckResult(
            this,
            Risk.MODERATE,
            "Not implemented (IP: ${ipClient.getPublicIP()})"
        )
    }
}