package edu.ntnu.tobiasth.securitydashboard

import edu.ntnu.tobiasth.securitydashboard.service.HomeAssistantService
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import java.time.Instant
import io.quarkus.logging.Log

@Path("/api/logs")
class LogsResource(
    val haService: HomeAssistantService
) {
    @GET
    fun get(): Map<String, List<String>> {
        val logs = haService.getAddonLogs()

        logs["Home Assistant Core"] = haService.getCoreLogs()
        logs["Home Assistant Supervisor"] = haService.getSupervisorLogs()

        return logs
    }

    @GET
    @Path("/unified")
    fun getUnified(): List<String> {
        val logs = mutableMapOf<Instant, String>()

        logs.putAll(parse(haService.getCoreLogs()))
        logs.putAll(parse(haService.getSupervisorLogs()))
        logs.putAll(parse(haService.getAddonLogs().values.flatten()))

        return logs.toSortedMap().values.toList()
    }

    private fun parse(entries: List<String>): Map<Instant, String> {
         return entries.filter { it.isNotBlank() }.associateBy {
             try {
                val split = it.split(" ")
                val date = split[0]
                val time = split[1]
                return@associateBy Instant.parse("${date}T${time}Z")
            } catch (e: Exception) {
                Log.debug("Unable to parse log entry: '$it'")
                return@associateBy Instant.now()
            }
        }
    }
}