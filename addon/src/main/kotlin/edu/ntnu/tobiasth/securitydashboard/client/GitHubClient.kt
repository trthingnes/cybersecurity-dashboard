package edu.ntnu.tobiasth.securitydashboard.client

import edu.ntnu.tobiasth.securitydashboard.client.dto.github.RepositorySecurityAdvisory
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient(configKey = "github-api")
interface GitHubClient {
    @GET
    @Path("/repos/{owner}/{repository}/security-advisories")
    fun getSecurityAdvisories(@PathParam("owner") owner: String, @PathParam("repository") repository: String): List<RepositorySecurityAdvisory>
}