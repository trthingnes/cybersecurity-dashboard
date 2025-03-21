package edu.ntnu.tobiasth.securitydashboard.service

import edu.ntnu.tobiasth.securitydashboard.client.GitHubClient
import edu.ntnu.tobiasth.securitydashboard.client.dto.github.RepositorySecurityAdvisory
import edu.ntnu.tobiasth.securitydashboard.util.VersionComparator
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.rest.client.inject.RestClient

@ApplicationScoped
class GitHubService(
    @RestClient val githubClient: GitHubClient,
    val versionComparator: VersionComparator
) {
    fun getActiveSecurityAdvisories(owner: String, repository: String): List<RepositorySecurityAdvisory> =
        githubClient.getSecurityAdvisories(owner, repository)

    fun getActiveCveAdvisories(owner: String, repository: String, packageName: String, packageVersion: String) =
        getActiveSecurityAdvisories("home-assistant", "core")
            .filter { it.cveId != null }
            .filter { a ->
                a.vulnerabilities.any { v ->
                    try {
                        v.image.ecosystem == packageName && v.patchedVersions != null && versionComparator.isOlderThan(
                            packageVersion,
                            v.patchedVersions
                        )
                    } catch (e: NumberFormatException) {
                        false // Ignore any patched versions that are non-numeric.
                    }
                }
            }

}