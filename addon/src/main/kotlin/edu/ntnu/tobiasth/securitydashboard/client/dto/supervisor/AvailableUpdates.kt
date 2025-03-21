package edu.ntnu.tobiasth.securitydashboard.client.dto.supervisor

import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class AvailableUpdates(
    val availableUpdates: List<Update>
) {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Update(
        val name: String?,
        val updateType: UpdateType,
        val versionLatest: String
    )

    enum class UpdateType(@JsonValue val value: String) {
        OS("os"),
        SUPERVISOR("supervisor"),
        CORE("core"),
        ADDON("addon")
    }
}

