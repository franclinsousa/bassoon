package `in`.francl.bassoon.infrastructure.api

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import org.slf4j.LoggerFactory

class IpApi {

    private val logger = LoggerFactory.getLogger(IpApi::class.java)
    private val client: HttpClient

    init {
        client = HttpClient(CIO) {
            install(JsonFeature) {
                serializer = GsonSerializer()
            }
        }
    }

    suspend fun getInfo(ip: String): Map<String, String> {
        val url = "http://ip-api.com/json/$ip?fields=status,message,continent,continentCode,country,countryCode,region,regionName,city,district,zip,lat,lon,timezone,offset,currency,isp,org,as,asname,reverse,mobile,proxy,hosting,query"
        logger.info("Obtendo informações do ip $ip em ip-api.com.")
        val response: Map<String, String> = client.get(url)
        response["status"]?.let {
            if (it.contains("fail")) logger.error("Houve um problema com a requisição [query: ${response["query"]}, message: ${response["message"]}]")
        }
        return response
    }

}
