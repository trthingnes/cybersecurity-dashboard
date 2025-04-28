package edu.ntnu.tobiasth.securitydashboard.client.dto.supervisor

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.Instant

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class Backups(
    val backups: List<Backup>,
    val daysUntilStale: Int
) {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Backup(
        val slug: String,
        val name: String,
        val date: Instant,
        val type: String,
        val size: Float,
        val location: String?,
        val protected: Boolean,
        val compressed: Boolean,
        val content: Map<String, Any>
    )
}