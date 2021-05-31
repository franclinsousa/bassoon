package `in`.francl.bassoon.interfaces.api

import `in`.francl.bassoon.application.LinkService
import `in`.francl.bassoon.application.StatService
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

class LinkController(
    private val service: LinkService,
    route: Route,
) {

    private val regexpValidURL =
        "((https?://)|(ftp://)|(^))([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+([a-zA-Z]{2,9})(:\\d{1,4})?([-\\w/#~:.?+=&%@~]*)"


    init {
        route.route("") {
            short()
        }
    }


    private fun Route.short() = post {
        val urlShorten = call.receive<Map<String, String>>()["url"]
        val isValidUrl = urlShorten?.matches(Regex(regexpValidURL)) ?: false
        val shortener = urlShorten?.let {
            val urlOrigin = call.request.origin.let { o ->
                when {
                    o.host.contains("localhost") -> "http://${o.host}:${o.port}"
                    else -> "${o.scheme}://${o.host}"
                }
            }
            if (isValidUrl) service.create(it, urlOrigin) else null
        }
        when {
            !isValidUrl -> call.respond(HttpStatusCode.BadRequest, ("error" to "URL ($urlShorten) inválida."))
            shortener == null -> call.respond(
                HttpStatusCode.InternalServerError,
                ("error" to "Houve um problema no servidor.")
            )
            else -> call.respond(HttpStatusCode.Created, shortener)
        }
    }

}
