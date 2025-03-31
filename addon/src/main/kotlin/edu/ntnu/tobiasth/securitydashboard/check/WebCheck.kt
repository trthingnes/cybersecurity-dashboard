package edu.ntnu.tobiasth.securitydashboard.check

import edu.ntnu.tobiasth.securitydashboard.service.OptionsService
import edu.ntnu.tobiasth.securitydashboard.service.dto.Risk
import jakarta.enterprise.context.ApplicationScoped
import okhttp3.OkHttpClient
import okhttp3.Request

@ApplicationScoped
class WebCheck(
    val optionsService: OptionsService
) : Check() {
    override val id = "web-check"
    override val name = "Web Interface"
    override val description = ""
    override val mitigation = ""

    val client = OkHttpClient()

    override fun check() {
        val request = Request.Builder()
            .url(optionsService.instanceUrl.orElseThrow { IllegalArgumentException("No URL provided") }).build()
        val response = client.newCall(request).execute()

        if (response.request.url.isHttps) {
            yield(result("Web Interface HTTPS", Risk.LOW, "Web interface is secured with HTTPS."))
        } else {
            yield(result("Web Interface HTTPS", Risk.HIGH, "Web interface is not secured with HTTPS."))
        }

        response.close()
    }
}