package edu.ntnu.tobiasth.securitydashboard.client.dto.supervisor

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class Addons(
    val addons: List<SupervisorAddon>
) {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class SupervisorAddon(
        val name: String,
        val slug: String,
        val description: String,
        val advanced: Boolean,
        val stage: String,
        val version: String,
        val versionLatest: String,
        val updateAvailable: Boolean,
        val available: Boolean,
        val detached: Boolean,
        val state: String,
        val repository: String,
        val build: Boolean,
        val url: String?,
        val icon: Boolean,
        val systemManaged: Boolean
    )
}
