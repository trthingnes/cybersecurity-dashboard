package edu.ntnu.tobiasth.securitydashboard.service

import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.config.inject.ConfigProperty

@ApplicationScoped
data class OptionsService(
    @ConfigProperty(name = "OPTION_INSTANCE_URL", defaultValue = "localhost") val instanceUrl: String
)