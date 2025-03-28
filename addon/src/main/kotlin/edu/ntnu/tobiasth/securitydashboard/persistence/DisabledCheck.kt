package edu.ntnu.tobiasth.securitydashboard.persistence

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.UniqueConstraint

@Entity
class DisabledCheck() : PanacheEntity() {
    companion object : PanacheCompanion<DisabledCheck>
    @Column(nullable = false, unique = true)
    final lateinit var checkId: String

    constructor(checkId: String) : this() {
        this.checkId = checkId
    }
}
