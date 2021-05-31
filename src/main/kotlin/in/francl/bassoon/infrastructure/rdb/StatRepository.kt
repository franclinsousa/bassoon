package `in`.francl.bassoon.infrastructure.rdb

import `in`.francl.bassoon.domain.model.Stat
import org.jetbrains.exposed.sql.transactions.transaction

class StatRepository {

    fun create(stat: Stat): StatEntity {
        return transaction {
            StatEntity.new {
                this.link = stat.link as LinkEntity
                this.ip = stat.ip
                this.continent = stat.continent
                this.continentCode = stat.continentCode
                this.country = stat.country
                this.countryCode = stat.countryCode
                this.region = stat.region
                this.regionName = stat.regionName
                this.city = stat.city
                this.district = stat.district
                this.zip = stat.zip
                this.lat = stat.lat
                this.lon = stat.lon
                this.timezone = stat.timezone
                this.offset = stat.offset
                this.currency = stat.currency
                this.isp = stat.isp
                this.org = stat.org
                this.`as` = stat.`as`
                this.asname = stat.asname
            }
        }
    }

    fun findByLinkId(linkId: Long): Collection<StatEntity> {
        return transaction {
            StatEntity.find {
                StatEntity.Table.link eq linkId
            }.toList()
        }
    }

}
