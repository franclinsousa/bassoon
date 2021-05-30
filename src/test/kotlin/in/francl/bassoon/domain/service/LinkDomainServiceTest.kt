package `in`.francl.bassoon.domain.service

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*


internal class LinkDomainServiceTest {

    private val serviceTest = LinkDomainService()

    @Test
    fun encode() {
        val id = 3L
        val expected = "d"

        val result = serviceTest.encode(id)

        assertEquals(expected, result, "Given id = $id, when ShortenerDomainService.encode($id), then $result == $expected.")
    }

    @Test
    fun decode() {
        val url = "d"
        val expected = 3L

        val result = serviceTest.decode(url)

        assertEquals(expected, result)
    }

}
