package edu.ntnu.tobiasth.securitydashboard.check

import edu.ntnu.tobiasth.securitydashboard.service.dto.CheckResult
import edu.ntnu.tobiasth.securitydashboard.service.dto.Risk
import edu.ntnu.tobiasth.securitydashboard.service.IpService
import edu.ntnu.tobiasth.securitydashboard.service.OptionsService
import jakarta.enterprise.context.ApplicationScoped
import java.net.InetAddress

@ApplicationScoped
class ProxyCheck(
    val optionsService: OptionsService,
    val ipService: IpService
) : Check {
    override val name: String
        get() = "Remote Access Proxy"
    override val description: String
        get() = "Home Assistant is accessed through a proxy."

    override fun run(): CheckResult {
        if (optionsService.instanceUrl == "localhost") {
            return result(Risk.LOW, "Home Assistant is configured for local access only.")
        }

        val publicIp = ipService.getPublicIP()
        val instanceUrl = optionsService.instanceUrl
        return if (InetAddress.getAllByName(instanceUrl).any { it.hostAddress == publicIp }) {
            result(Risk.HIGH, "Home Assistant is accessed directly.")
        } else result(Risk.LOW, "Home Assistant is accessed through a proxy.")
    }
}