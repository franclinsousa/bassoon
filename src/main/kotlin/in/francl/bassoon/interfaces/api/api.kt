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
        val statService = StatService(StatRepository(), LinkDomainService(), IpApi())
        val linkService = LinkService(LinkRepository(), LinkDomainService(), statService)
        route("/api/v1/shorten") {
            LinkController(linkService, this)
            StatController(statService, this)
        }
        route("/") {
            Controller(linkService, this)
        }
    }

}
