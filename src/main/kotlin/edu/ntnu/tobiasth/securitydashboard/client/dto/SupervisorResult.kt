package edu.ntnu.tobiasth.securitydashboard.client.dto

data class SupervisorResult<T>(
    val result: String,
    val data: T,
)