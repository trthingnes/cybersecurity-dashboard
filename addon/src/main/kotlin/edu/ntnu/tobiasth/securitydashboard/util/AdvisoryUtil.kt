package edu.ntnu.tobiasth.securitydashboard.util

import edu.ntnu.tobiasth.securitydashboard.client.dto.github.RepositorySecurityAdvisory
import edu.ntnu.tobiasth.securitydashboard.service.dto.Risk
import jakarta.inject.Singleton

@Singleton
class AdvisoryUtil(
    private val versionComparator: VersionComparator
) {
    fun getPatchedVersion(advisories: List<RepositorySecurityAdvisory>) = advisories
        .flatMap { it.vulnerabilities }
        .mapNotNull { it.patchedVersions }
        .sortedWith(versionComparator)
        .last()

    fun getRisk(advisories: List<RepositorySecurityAdvisory>) = if (advisories.any {
            it.severity == RepositorySecurityAdvisory.Severity.CRITICAL || it.severity == RepositorySecurityAdvisory.Severity.HIGH
        }) Risk.HIGH else Risk.MODERATE
}