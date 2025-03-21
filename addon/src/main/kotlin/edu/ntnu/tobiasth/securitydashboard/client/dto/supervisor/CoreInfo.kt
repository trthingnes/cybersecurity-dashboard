package edu.ntnu.tobiasth.securitydashboard.client.dto.supervisor

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CoreInfo(
    val version: String,
    val versionLatest: String,
    val updateAvailable: Boolean,
    val arch: String,
    val machine: String,
    val ipAddress: String,
    val image: String,
    val boot: Boolean,
    val port: Int,
    val ssl: Boolean,
    val watchdog: Boolean,
    val waitBoot: Int,
    val audioInput: String?,
    val audioOutput: String?,
    val backupsExcludeDatabase: Boolean
)