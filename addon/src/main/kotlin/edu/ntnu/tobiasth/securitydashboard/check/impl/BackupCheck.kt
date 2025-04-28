package edu.ntnu.tobiasth.securitydashboard.check.impl

import edu.ntnu.tobiasth.securitydashboard.check.Check
import edu.ntnu.tobiasth.securitydashboard.check.Risk
import edu.ntnu.tobiasth.securitydashboard.service.HomeAssistantService
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class BackupCheck(
    private val haService: HomeAssistantService
) : Check() {
    override val id = "backups-check"
    override val name = "Backups"
    override val description = "It's recommended back Home Assistant up to two different physical locations in case of serious system failures."
    override val mitigation = "Use the built-in backup service to schedule and run backups using your preferred storage integration."
    override val keywords = listOf("Backups")

    override fun check() {
        val backupsInfo = haService.getBackupsInfo()

        if (backupsInfo.backups.isEmpty()) {
            yield(result(Risk.HIGH, "There are no backups."))
            return
        }

        if (backupsInfo.backups.all { it.location == null }) {
            yield(result(Risk.MODERATE, "Backups are only stored locally."))
            return
        }

        if (backupsInfo.daysUntilStale == 0) {
            yield(result(Risk.MODERATE, "Backups are stale."))
            return
        }

        if (backupsInfo.daysUntilStale <= 7) {
            yield(result(Risk.LOW, "Backups will become stale in less than 7 days."))
            return
        }

        yield(result(Risk.NONE, "Backups are current and stored in multiple locations."))
    }

}