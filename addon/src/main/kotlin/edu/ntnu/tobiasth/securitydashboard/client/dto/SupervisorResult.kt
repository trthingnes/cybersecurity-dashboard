package edu.ntnu.tobiasth.securitydashboard.client.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class SupervisorResult<T>(
    val result: String,
    val data: T,
)