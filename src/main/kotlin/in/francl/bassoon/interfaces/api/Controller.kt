package `in`.francl.bassoon.interfaces.api

import `in`.francl.bassoon.application.LinkService
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*

class Controller(
    private val service: LinkService,
    route: Route,
) {

    init {
        route.route("{shorten}") {
            post { redirect() }
            get { redirect() }
            put { redirect() }
        }
    }

    private suspend fun PipelineContext<Unit, ApplicationCall>.redirect() {
        val shorten = call.parameters["shorten"]
        val target = shorten?.let { service.getTargetUrl(it, call.request.header("X-Forwarded-For") ?: call.request.origin.remoteHost) }
        when (target) {
            null -> call.respond(HttpStatusCode.BadRequest, mapOf("error" to "URL inválida."))
            else -> call.respondRedirect(target, true)
        }
    }


}
