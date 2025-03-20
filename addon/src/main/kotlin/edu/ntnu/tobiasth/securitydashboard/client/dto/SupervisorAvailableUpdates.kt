package edu.ntnu.tobiasth.securitydashboard.client.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class SupervisorAvailableUpdates(
    val availableUpdates: List<Update>
) {
    data class Update(
        val name: String?,
        val updateType: UpdateType,
        val versionLatest: String
    )

    enum class UpdateType {
        OS,
        SUPERVISOR,
        CORE,
        ADDON
    }
}

