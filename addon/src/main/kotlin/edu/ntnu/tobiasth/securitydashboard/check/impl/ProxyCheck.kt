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
    override val description = "Since the risks of exposing a ports on a home network are significant, it's recommended to setup remote access to Home Assistant through some kind of proxy. This means that connections to Home Assistant are passing through another server before reaching the instance."
    override val mitigation = "The safest way to run Home Assistant is to only allow connections from the local network, however this eliminates a lot of the convenience by forcing you to be at home to access Home Assistant. The second best option is to expose Home Assistant to the internet through a proxy. This is possible using both paid services like Home Assistant Cloud, or other (sometimes) free third-party services."

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