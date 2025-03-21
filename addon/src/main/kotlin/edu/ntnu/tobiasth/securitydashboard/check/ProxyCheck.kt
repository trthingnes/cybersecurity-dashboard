package edu.ntnu.tobiasth.securitydashboard.check

import edu.ntnu.tobiasth.securitydashboard.service.dto.CheckResult
import edu.ntnu.tobiasth.securitydashboard.service.dto.Risk
import edu.ntnu.tobiasth.securitydashboard.service.IpService
import edu.ntnu.tobiasth.securitydashboard.service.OptionsService
import jakarta.enterprise.context.ApplicationScoped
import java.net.InetAddress
import kotlin.jvm.optionals.getOrElse

@ApplicationScoped
class ProxyCheck(
    val optionsService: OptionsService,
    val ipService: IpService
) : Check() {
    override val name = "Remote Access Proxy"
    override val description = "Home Assistant is accessed through a proxy."

    override fun check(): CheckResult {
        val instanceUrl = optionsService.instanceUrl.getOrElse { "" }

        if (instanceUrl.isBlank()) return result(Risk.UNKNOWN, "Instance URL has not been configured.")

        if (instanceUrl == "localhost") {
            return result(Risk.LOW, "Home Assistant is configured for local access only.")
        }

        val publicIp = ipService.getPublicIP()

        return if (InetAddress.getAllByName(instanceUrl).any { it.hostAddress == publicIp }) {
            result(Risk.HIGH, "Home Assistant is accessed directly.")
        } else result(Risk.LOW, "Home Assistant is accessed through a proxy.")
    }
}