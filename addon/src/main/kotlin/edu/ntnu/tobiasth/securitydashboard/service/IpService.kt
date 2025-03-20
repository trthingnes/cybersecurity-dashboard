package edu.ntnu.tobiasth.securitydashboard.service

import edu.ntnu.tobiasth.securitydashboard.client.CloudflareClient
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.rest.client.inject.RestClient

@ApplicationScoped
class IpService {
    @RestClient
    private lateinit var client: CloudflareClient

    fun getPublicIP(): String? {
        val trace = client.getTrace()
            .split("\n")
            .associateByTo(mutableMapOf(), { it.substringBefore('=') }, { it.substringAfter('=') })

        return trace["ip"]
    }
}