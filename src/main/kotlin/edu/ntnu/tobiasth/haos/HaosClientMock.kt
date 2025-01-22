package edu.ntnu.tobiasth.haos

import edu.ntnu.tobiasth.haos.dto.CoreInfoDto
import io.quarkus.arc.profile.UnlessBuildProfile
import jakarta.enterprise.context.ApplicationScoped

@UnlessBuildProfile("prod")
@ApplicationScoped
class HaosClientMock : HaosClient {
    override fun getCoreInfo() = CoreInfoDto(
        version = "2025.01",
        versionLatest= "2025.02",
        updateAvailable = "2025.02",
        arch = "amd64",
        machine = "Linux",
        ipAddress = "0.0.0.0",
        image = "alpine:3.18",
        boot = true,
        port = 8123,
        ssl = false,
        watchdog = false,
        waitBoot = 0,
        audioInput = null,
        audioOutput = null,
        backupsExcludeDatabase = true,
    )
}