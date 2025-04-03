package edu.ntnu.tobiasth.securitydashboard.check.impl

import edu.ntnu.tobiasth.securitydashboard.check.Check
import edu.ntnu.tobiasth.securitydashboard.check.Risk
import edu.ntnu.tobiasth.securitydashboard.service.OptionsService
import jakarta.enterprise.context.ApplicationScoped
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.net.ConnectException
import java.net.URI

@ApplicationScoped
class HttpsCheck(
    val optionsService: OptionsService
) : Check() {
    override val id = "https-check"
    override val name = "Remote Access Encryption"
    override val description = "To ensure that information about your home is not transferred in a clear text over the internet, it is important to ensure that Home Assistant only allows encrypted remote connections."
    override val mitigation = "Home Assistant should be setup to not allow HTTP remote connections, and should provide clients with a valid certificate for HTTPS. There are many ways to achieve this, like using Let's Encrypt (Certbot) or connecting to Home Assistant through a proxy that enables HTTPS by default."

    val client = OkHttpClient()

    override fun check() {
        val instanceUrl = optionsService.instanceUrl.orElse(null)?.let { URI(it).toURL() }

        if (instanceUrl == null) {
            yield(result(Risk.UNKNOWN, "Instance URL has not been configured."))
            return
        }

        val request = Request.Builder().url(instanceUrl).build()
        var response: Response? = null

        try {
            response = client.newCall(request).execute()
            response.close()
        } catch (_: ConnectException) {
            yield(result(Risk.UNKNOWN, "Unable to connect to Home Assistant."))
        }

        if (instanceUrl.host == "localhost") {
            yield(result(Risk.LOW, "Home Assistant is configured for local access only."))
            return
        }

        if (response?.handshake == null) {
            yield(result(Risk.HIGH, "Home Assistant is not secured with HTTPS."))
        } else {
            yield(result(Risk.LOW, "Home Assistant is secured with HTTPS."))
        }
    }
}