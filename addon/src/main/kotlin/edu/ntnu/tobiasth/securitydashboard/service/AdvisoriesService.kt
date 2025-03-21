package edu.ntnu.tobiasth.securitydashboard.service

import edu.ntnu.tobiasth.securitydashboard.client.GitHubClient
import edu.ntnu.tobiasth.securitydashboard.client.dto.github.RepositorySecurityAdvisories
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.rest.client.inject.RestClient

@ApplicationScoped
class AdvisoriesService(
    @RestClient val githubClient: GitHubClient
) {
    fun getSecurityAdvisories(owner: String, repository: String): List<RepositorySecurityAdvisories> =
        githubClient.getSecurityAdvisories(owner, repository)
}