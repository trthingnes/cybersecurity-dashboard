package edu.ntnu.tobiasth.securitydashboard.check.impl

import edu.ntnu.tobiasth.securitydashboard.check.Check
import edu.ntnu.tobiasth.securitydashboard.check.Risk
import edu.ntnu.tobiasth.securitydashboard.service.OptionsService
import jakarta.enterprise.context.ApplicationScoped
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.ConnectException
import java.net.URI
import java.security.cert.*

@ApplicationScoped
class CertificateCheck(
    val optionsService: OptionsService
) : Check() {
    override val id = "cert-check"
    override val name = "Remote Access Certificate"
    override val description =
        "To ensure that information about your home is not transferred in an insecure way over the internet it's important to use a valid trusted HTTPS certificate."
    override val mitigation =
        "Home Assistant should be setup to not allow HTTP remote connections, and should provide clients with a valid trusted certificate for HTTPS. There are many ways to achieve this, like using Let's Encrypt (Certbot) or connecting to Home Assistant through a proxy that enables HTTPS by default."

    val client = OkHttpClient()

    override fun check() {
        val instanceUrl = optionsService.instanceUrl.orElse(null)?.let { URI(it).toURL() }

        if (instanceUrl == null) {
            yield(result(Risk.UNKNOWN, "Instance URL has not been configured."))
            return
        }

        if (instanceUrl.host == "localhost") {
            yield(result(Risk.NONE, "Home Assistant is configured for local access only."))
            return
        }

        // The certificate list will be ordered based on the trust chain
        val certificates = mutableListOf<X509Certificate>()
        val request = Request.Builder().url(instanceUrl).build()

        try {
            val response = client.newCall(request).execute()
            response.handshake?.let { certificates.addAll(it.peerCertificates.filterIsInstance<X509Certificate>()) }
            response.close()
        } catch (_: ConnectException) {
            yield(result(Risk.UNKNOWN, "Unable to connect to Home Assistant."))
            return
        }

        if (certificates.isEmpty()) {
            yield(result(Risk.UNKNOWN, "No certificates to check."))
            return
        }

        val cert = certificates.first()

        try {
            cert.checkValidity()
        } catch (_: CertificateExpiredException) {
            yield(result(Risk.HIGH, "Home Assistant certificate is expired."))
            return
        } catch (_: CertificateNotYetValidException) {
            yield(result(Risk.MODERATE, "Home Assistant certificate is not valid yet."))
            return
        }

        if (cert.issuerX500Principal == cert.subjectX500Principal) {
            yield(result(Risk.MODERATE, "Home Assistant certificate is self-signed."))
            return
        }

        yield(result(Risk.NONE, "Home Assistant certificate is valid and signed by CA."))
    }
}