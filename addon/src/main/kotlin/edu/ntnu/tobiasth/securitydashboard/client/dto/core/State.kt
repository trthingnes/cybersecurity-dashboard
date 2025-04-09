package edu.ntnu.tobiasth.securitydashboard.client.dto.core

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.Instant

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class State(
    val entityId: String,
    val state: String,
    val attributes: Attributes,
    val lastChanged: Instant,
    val lastReported: Instant,
    val lastUpdated: Instant,
    val context: Map<String, String>
) {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Attributes(val friendlyName: String?)
}
