package `in`.francl.bassoon.infrastructure.api

import `in`.francl.bassoon.domain.model.Stat
import `in`.francl.bassoon.domain.model.StatModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class IpApi {

    val client: HttpClient

    init {
        client = HttpClient(CIO) {
            install(JsonFeature) {
                serializer = GsonSerializer()
            }
        }
    }

    suspend fun getInfo(ip: String): Map<String, String> {
        val url = "http://ip-api.com/json/$ip?fields=status,message,continent,continentCode,country,countryCode,region,regionName,city,district,zip,lat,lon,timezone,offset,currency,isp,org,as,asname,reverse,mobile,proxy,hosting,query"
        return client.get(url)
    }

}
