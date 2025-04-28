package edu.ntnu.tobiasth.securitydashboard.check.impl

import edu.ntnu.tobiasth.securitydashboard.check.Check
import edu.ntnu.tobiasth.securitydashboard.check.Risk
import edu.ntnu.tobiasth.securitydashboard.service.IpService
import edu.ntnu.tobiasth.securitydashboard.service.OptionsService
import jakarta.enterprise.context.ApplicationScoped
import java.net.InetAddress
import java.net.URI

@ApplicationScoped
class ProxyCheck(
    val optionsService: OptionsService,
    val ipService: IpService
) : Check() {
    override val id = "proxy-check"
    override val name = "Remote Access Proxy"
    override val description = "It's recommended to remotely access Home Assistant through a reverse proxy, i.e. another server, due to the risks of exposing ports in a home network."
    override val mitigation = "Only allow connections from the local network, or expose Home Assistant through a reverse proxy using Home Assistant Cloud, Cloudflare, or similar services."

    override fun check() {
        val instanceUrl = optionsService.instanceUrl.orElse(null)?.let { URI(it).toURL() }

        if (instanceUrl == null) {
            yield(result(Risk.UNKNOWN, "Instance URL has not been configured."))
        } else if (instanceUrl.host == "localhost") {
            yield(result(Risk.NONE, "Home Assistant is configured for local access only."))
        } else {
            val publicIp = ipService.getPublicIP()
            if (InetAddress.getAllByName(instanceUrl.host).any { it.hostAddress == publicIp }) {
                yield(result(Risk.HIGH, "Home Assistant is accessed directly."))
            } else yield(result(Risk.NONE, "Home Assistant is accessed through a proxy."))
        }
    }
}