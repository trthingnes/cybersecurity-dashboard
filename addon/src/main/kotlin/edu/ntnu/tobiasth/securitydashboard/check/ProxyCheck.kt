package edu.ntnu.tobiasth.securitydashboard.check

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
    override val mitigation = "The safest way to run Home Assistant is to only allow connections from the local network, however this eliminates a lot of the convenience by forcing you to be at home to access Home Assistant. The second best option is to expose Home Assistant to the internet through a proxy. This is possible using both paid services like Home Assistant Cloud, or other (sometimes) free third-party services."

    override fun check() {
        val instanceUrl = optionsService.instanceUrl.getOrElse { "" }

        if (instanceUrl.isBlank()) {
            yield(result(Risk.UNKNOWN, "Instance URL has not been configured."))
        } else if (instanceUrl == "localhost") {
            yield(result(Risk.LOW, "Home Assistant is configured for local access only."))
        } else {
            val publicIp = ipService.getPublicIP()
            if (InetAddress.getAllByName(instanceUrl).any { it.hostAddress == publicIp }) {
                yield(result(Risk.HIGH, "Home Assistant is accessed directly."))
            } else yield(result(Risk.LOW, "Home Assistant is accessed through a proxy."))
        }
    }
}