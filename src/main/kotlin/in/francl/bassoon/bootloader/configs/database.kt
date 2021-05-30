package `in`.francl.bassoon.bootloader.configs

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

val config by lazy {
    HikariConfig()
        .apply {
            jdbcUrl = "jdbc:sqlite:file:bassoon?cache=shared"
            driverClassName = "org.sqlite.JDBC"
            transactionIsolation = "TRANSACTION_SERIALIZABLE"
            validate()
        }
}

val dataSource by lazy { HikariDataSource(config) }

fun initDatabase() {
    Database.connect(dataSource)
}
