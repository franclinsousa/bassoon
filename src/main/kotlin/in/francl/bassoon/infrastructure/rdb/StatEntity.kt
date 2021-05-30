package `in`.francl.bassoon.infrastructure.rdb

import `in`.francl.bassoon.domain.model.Link
import `in`.francl.bassoon.domain.model.Stat
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable


class StatEntity(id: EntityID<Long>) : Entity<Long>(id), Stat {

    override var link by LinkEntity referencedOn Table.link
    override var ip: String by Table.ip
    override var continent: String by Table.continent
    override var continentCode: String by Table.continentCode
    override var country: String by Table.country
    override var countryCode: String by Table.countryCode
    override var region: String by Table.region
    override var regionName: String by Table.regionName
    override var city: String by Table.city
    override var district: String by Table.district
    override var zip: String by Table.zip
    override var lat: Double by Table.lat
    override var lon: Double by Table.lon
    override var timezone: String by Table.timezone
    override var offset: String by Table.offset
    override var currency: String by Table.currency
    override var isp: String by Table.isp
    override var org: String by Table.org
    override var `as`: String by Table.`as`
    override var asname: String by Table.asname

    companion object : EntityClass<Long, StatEntity>(Table)

    object Table : LongIdTable("stats") {
        val link = reference("link_id", LinkEntity.Table)
        val ip = varchar("ip", 1024)
        val continent = varchar("continent", 1024)
        val continentCode = varchar("continentCode", 1024)
        val country = varchar("country", 1024)
        val countryCode = varchar("countryCode", 1024)
        val region = varchar("region", 1024)
        val regionName = varchar("regionName", 1024)
        val city = varchar("city", 1024)
        val district = varchar("district", 1024)
        val zip = varchar("zip", 1024)
        val lat = double("lat")
        val lon = double("lon")
        val timezone = varchar("timezone", 1024)
        val offset = varchar("offset", 1024)
        val currency = varchar("currency", 1024)
        val isp = varchar("isp", 1024)
        val org = varchar("org", 1024)
        val `as` = varchar("'as'", 1024)
        val asname = varchar("asname", 1024)
    }

}
