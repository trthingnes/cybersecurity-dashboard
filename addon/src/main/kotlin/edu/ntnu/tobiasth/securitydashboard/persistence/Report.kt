package edu.ntnu.tobiasth.securitydashboard.persistence

import edu.ntnu.tobiasth.securitydashboard.check.CheckResult
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import java.time.Instant

@Entity
class Report() : PanacheEntity() {
    companion object : PanacheCompanion<Report>

    @Column(nullable = false, unique = true)
    final lateinit var timestamp: Instant

    @ElementCollection(fetch = FetchType.EAGER)
    final lateinit var results: List<CheckResult>

    @Column(nullable = false, unique = false)
    final lateinit var tier: Tier

    @Column(nullable = false, unique = false)
    final var tierCompletion: Float = 1.0F

    @Column(nullable = false, unique = false)
    final var tierAdvanceIn: Int = 0

    constructor(
        timestamp: Instant,
        results: List<CheckResult>,
        tier: Tier,
        tierCompletion: Float,
        tierAdvanceIn: Int
    ) : this() {
        this.timestamp = timestamp
        this.results = results
        this.tier = tier
        this.tierCompletion = tierCompletion
        this.tierAdvanceIn = tierAdvanceIn
    }

    enum class Tier {
        GOLD,
        SILVER,
        BRONZE
    }
}
