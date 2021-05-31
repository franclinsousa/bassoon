package `in`.francl.bassoon.application

import `in`.francl.bassoon.domain.model.Link
import `in`.francl.bassoon.domain.model.StatModel
import `in`.francl.bassoon.domain.service.LinkDomainService
import `in`.francl.bassoon.infrastructure.api.IpApi
import `in`.francl.bassoon.infrastructure.rdb.StatEntity
import `in`.francl.bassoon.infrastructure.rdb.StatRepository
import org.jetbrains.exposed.dao.load
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory


class StatService(
    private val repository: StatRepository,
    private val linkDomainService: LinkDomainService,
    private val ipApi: IpApi,
) {

    private val logger = LoggerFactory.getLogger(StatService::class.java)

    suspend fun create(link: Link, ip: String) {
        val stat = ipApi.getInfo(ip)
        val statModel = StatModel(
            link = link,
            ip = stat["query"] ?: ip,
            continent = stat["continent"] ?: "",
            continentCode = stat["continentCode"] ?: "",
            country = stat["country"] ?: "",
            countryCode = stat["countryCode"] ?: "",
            region = stat["region"] ?: "",
            regionName = stat["regionName"] ?: "",
            city = stat["city"] ?: "",
            district = stat["district"] ?: "",
            zip = stat["zip"] ?: "",
            lat = stat["lat"]?.toDoubleOrNull() ?: 0.0,
            lon = stat["lon"]?.toDoubleOrNull() ?: 0.0,
            timezone = stat["timezone"] ?: "",
            offset = stat["offset"] ?: "",
            currency = stat["currency"] ?: "",
            isp = stat["isp"] ?: "",
            org = stat["org"] ?: "",
            `as` = stat["as"] ?: "",
            asname = stat["asname"] ?: "",
        )
        repository.create(statModel)
    }

    fun retrieveByShortUriEncoded(shortUriEncoded: String): Any {
        val linkId = linkDomainService.decode(shortUriEncoded)
        return transaction { repository.findByLinkId(linkId).map { StatModel.of(it.load(StatEntity::link)) } }
    }

}
