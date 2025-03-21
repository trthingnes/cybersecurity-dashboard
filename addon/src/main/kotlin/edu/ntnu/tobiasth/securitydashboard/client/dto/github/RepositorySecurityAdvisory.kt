package edu.ntnu.tobiasth.securitydashboard.client.dto.github

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonSetter
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.annotation.Nulls
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.net.URI
import java.time.Instant

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class RepositorySecurityAdvisory(
    val ghsaId: String,
    val cveId: String?,
    val url: URI,
    val htmlUrl: URI,
    val summary: String,
    val description: String?,
    val severity: Severity?,
    // Omitted author and publisher
    val identifiers: List<Identifier> = emptyList(),
    val state: State,
    val createdAt: Instant?,
    val updatedAt: Instant?,
    val publishedAt: Instant?,
    val closedAt: Instant?,
    val withdrawnAt: Instant?,
    // Omitted submission
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    val vulnerabilities: List<Vulnerability>,
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    val cvssSeverities: Map<String, CvssSeverity>,
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    val cwes: List<CWE>,
    // Omitted credits, creditsDetailed, collaboratingUsers, collaboratingTeams, privateFork
) {
    enum class Severity(@JsonValue val value: String) {
        CRITICAL("critical"),
        HIGH("high"),
        MEDIUM("medium"),
        LOW("low")
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Identifier(
        val value: String,
        val type: String
    )

    enum class State(@JsonValue val value: String) {
        PUBLISHED("published"),
        CLOSED("closed"),
        WITHDRAWN("withdrawn"),
        DRAFT("draft"),
        TRIAGE("triage")
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Vulnerability(
        @JsonAlias("package")
        val image: Image,
        val vulnerableVersionRange: String?,
        val patchedVersions: String?,
        @JsonSetter(nulls = Nulls.AS_EMPTY)
        val vulnerableFunctions: List<String>,
    )

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Image(
        val ecosystem: String,
        val name: String?,
    )

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class CvssSeverity(
        val vectorString: String?,
        val score: Float?,
    )

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class CWE(
        val cveId: String,
        val name: String,
    )
}
