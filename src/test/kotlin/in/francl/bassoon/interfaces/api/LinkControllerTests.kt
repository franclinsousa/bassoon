package `in`.francl.bassoon.interfaces.api

import `in`.francl.bassoon.application.LinkService
import `in`.francl.bassoon.bootloader.features.installFeatures
import `in`.francl.bassoon.domain.datatranfer.ShortLink
import `in`.francl.bassoon.domain.model.LinkModel
import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.routing.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito


internal class LinkControllerTests {

    private val service: LinkService = Mockito.mock(LinkService::class.java)

    @Test
    fun `create new short url successful`() {
        val shortener = LinkModel("https://francl.in")
        val expected = ShortLink("http://localhost/b")
        Mockito.`when`(service.create(Mockito.anyString(), Mockito.anyString())).thenReturn( expected )
        withTestApplication({
            installFeatures()
            routing {
                route("/api/v1") {
                    LinkController(service, this)
                }
            }
        }) {
            handleRequest(HttpMethod.Post, "/api/v1/short") {
                addHeader("Content-Type", "application/json")
                setBody(Gson().toJson(mapOf("url" to shortener.target)))
                addHeader("Accept", "application/json")
            }.apply {
                Assertions.assertEquals(HttpStatusCode.Created, response.status())
                Assertions.assertEquals(expected.url, Gson().fromJson(response.content, ShortLink::class.java).url)
            }
        }
    }

    @Test
    fun `try create new short url and fail`() {
        val shortener = LinkModel("https://francl")
        withTestApplication({
            installFeatures()
            routing {
                route("/api/v1") {
                    LinkController(service, this)
                }
            }
        }) {
            handleRequest(HttpMethod.Post, "/api/v1/short") {
                addHeader("Content-Type", "application/json")
                setBody(Gson().toJson(mapOf("url" to shortener.target)))
                addHeader("Accept", "application/json")
            }.apply {
                Assertions.assertEquals(HttpStatusCode.BadRequest, response.status())
                response.content?.contains("inválida")?.let { Assertions.assertTrue(it) }
            }
        }
    }


}
