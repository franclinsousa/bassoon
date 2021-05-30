package `in`.francl.bassoon.domain.model

class StatModel(
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
}