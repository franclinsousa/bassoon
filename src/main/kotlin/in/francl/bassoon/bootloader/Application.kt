package `in`.francl.bassoon.bootloader

import `in`.francl.bassoon.bootloader.configs.configure
import `in`.francl.bassoon.bootloader.features.installFeatures
import `in`.francl.bassoon.interfaces.initInterfaces
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configure()
        installFeatures()
        initInterfaces()
    }.start(wait = true)
}
