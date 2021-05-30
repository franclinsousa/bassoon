package `in`.francl.bassoon.interfaces.api

import `in`.francl.bassoon.application.LinkService
import `in`.francl.bassoon.application.StatService
import `in`.francl.bassoon.domain.service.LinkDomainService
import `in`.francl.bassoon.infrastructure.api.IpApi
import `in`.francl.bassoon.infrastructure.rdb.LinkRepository
import `in`.francl.bassoon.infrastructure.rdb.StatRepository
import io.ktor.application.*
import io.ktor.routing.*

fun Application.initAPI() {

    routing {
        val service = LinkService(LinkRepository(), LinkDomainService(), StatService(StatRepository(), IpApi()))
        route("/api/v1") {
            LinkController(service, this)
        }
        route("/") {
            Controller(service, this)
        }
    }

}
