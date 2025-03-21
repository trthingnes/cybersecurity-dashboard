package edu.ntnu.tobiasth.securitydashboard.client.dto.supervisor

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class SupervisorInfo(
    val version: String,
    val versionLatest: String,
    val updateAvailable: Boolean,
    val arch: String,
    val channel: String,
    val timezone: String,
    val healthy: Boolean,
    val supported: Boolean,
    val logging: String,
    val ipAddress: String,
    val waitBoot: Int,
    val debug: Boolean,
    val debugBlock: Boolean,
    val diagnostics: Boolean?,
    val addonRepositories: List<AddonRepository>,
    val autoUpdate: Boolean,
) {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class AddonRepository(
        val name: String,
        val slug: String,
    )
}