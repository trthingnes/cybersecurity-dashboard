package edu.ntnu.tobiasth.securitydashboard.service

import edu.ntnu.tobiasth.securitydashboard.client.GitHubClient
import edu.ntnu.tobiasth.securitydashboard.client.dto.github.RepositorySecurityAdvisory
import edu.ntnu.tobiasth.securitydashboard.util.VersionComparator
import io.quarkus.logging.Log
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.rest.client.inject.RestClient
import kotlin.jvm.optionals.getOrElse

@ApplicationScoped
class GitHubService(
    @RestClient val githubClient: GitHubClient,
    val optionsService: OptionsService,
    val versionComparator: VersionComparator
) {
    fun isAvailable() = optionsService.githubApiToken.getOrElse { "" }.isNotBlank()

    fun getSecurityAdvisories(owner: String, repository: String): List<RepositorySecurityAdvisory> =
        githubClient.getSecurityAdvisories(owner, repository)

    fun getActiveSecurityAdvisories(owner: String, repository: String, packageName: String, packageVersion: String) =
        getSecurityAdvisories(owner, repository)
            .filter { a ->
                a.vulnerabilities.any { v ->
                    Log.debug("Package name: $packageName, Vulnerable package name: ${v.vulnerablePackage.name}, Package version: $packageVersion, Patched versions: ${v.patchedVersions}")

                    if (v.vulnerablePackage.name == packageName && v.patchedVersions != null) {
                        try {
                            return@any versionComparator.isOlderThan(packageVersion, v.patchedVersions)
                        } catch (_: NumberFormatException) {}
                    }

                    false
                }
            }

}