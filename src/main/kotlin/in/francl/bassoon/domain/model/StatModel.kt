package `in`.francl.bassoon.domain.model


data class StatModel(
    override val link: Link,
    override val ip: String,
    override val continent: String,
    override val continentCode: String,
    override val country: String,
    override val countryCode: String,
    override val region: String,
    override val regionName: String,
    override val city: String,
    override val district: String,
    override val zip: String,
    override val lat: Double,
    override val lon: Double,
    override val timezone: String,
    override val offset: String,
    override val currency: String,
    override val isp: String,
    override val org: String,
    override val `as`: String,
    override val asname: String,
) : Stat {

    companion object {
        fun of(stat: Stat): Stat {
            return StatModel(
                link = LinkModel.of(stat.link),
                ip = stat.ip,
                continent = stat.continent,
                continentCode = stat.continentCode,
                country = stat.country,
                countryCode = stat.countryCode,
                region = stat.region,
                regionName = stat.regionName,
                city = stat.city,
                district = stat.district,
                zip = stat.zip,
                lat = stat.lat,
                lon = stat.lon,
                timezone = stat.timezone,
                offset = stat.offset,
                currency = stat.currency,
                isp = stat.isp,
                org = stat.org,
                `as` = stat.`as`,
                asname = stat.asname,
            )
        }
    }

}
