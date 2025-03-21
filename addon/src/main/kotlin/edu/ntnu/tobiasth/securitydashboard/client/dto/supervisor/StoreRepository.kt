package edu.ntnu.tobiasth.securitydashboard.client.dto.supervisor

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.net.URI

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class StoreRepository(
    val slug: String,
    val name: String,
    val source: String,
    val url: URI,
    val maintainer: String
)
