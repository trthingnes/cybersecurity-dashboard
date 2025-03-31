package edu.ntnu.tobiasth.securitydashboard.service

import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.config.inject.ConfigProperty
import java.util.*

@ApplicationScoped
data class OptionsService(
    @ConfigProperty(name = "OPTION_INSTANCE_URL") val instanceUrl: Optional<String>,
    @ConfigProperty(name = "OPTION_GITHUB_API_TOKEN") val githubApiToken: Optional<String>,
    @ConfigProperty(name = "OPTION_LOG_LEVEL") val logLevel: String
)