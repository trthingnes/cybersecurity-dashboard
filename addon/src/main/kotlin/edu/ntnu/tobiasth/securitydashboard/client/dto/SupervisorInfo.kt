package edu.ntnu.tobiasth.securitydashboard.client.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class SupervisorInfo(
    val supervisor: String,
    val homeassistant: String,
    val hassos: String?,
    val docker: String,
    val hostname: String?,
    val operatingSystem: String?,
    val features: List<String> = emptyList(),
    val machine: String,
    val arch: String,
    val supportedArch: List<String> = emptyList(),
    val supported: Boolean,
    val channel: String,
    val logging: String,
    val state: String,
    val timezone: String
)
