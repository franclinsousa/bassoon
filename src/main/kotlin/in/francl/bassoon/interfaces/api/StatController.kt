package `in`.francl.bassoon.interfaces.api

import `in`.francl.bassoon.application.StatService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

class StatController(
    private val service: StatService,
    route: Route
) {

    init {
        route.route("/{shorten}/stats") {
            stats()
        }
    }

    private fun Route.stats() = get {
        val shorten = call.parameters["shorten"]
        val stats = shorten?.let { it1 -> service.retrieveByShortUriEncoded(it1) }
        call.respond(HttpStatusCode.OK, stats ?: "")
    }

}
