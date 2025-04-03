package edu.ntnu.tobiasth.securitydashboard.check

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class CheckResult() {
    lateinit var id: String
    lateinit var risk: Risk
    lateinit var title: String
    lateinit var summary: String
    @Column(length = 512)
    lateinit var description: String
    @Column(length = 512)
    lateinit var mitigation: String

    constructor(
        id: String,
        risk: Risk,
        title: String,
        summary: String,
        description: String,
        mitigation: String
    ) : this() {
        this.id = id
        this.risk = risk
        this.title = title
        this.summary = summary
        this.description = description
        this.mitigation = mitigation
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CheckResult

        if (id != other.id) return false
        if (risk != other.risk) return false
        if (title != other.title) return false
        if (summary != other.summary) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + risk.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + summary.hashCode()
        return result
    }
}
