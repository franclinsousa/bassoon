package `in`.francl.bassoon.bootloader.features

import io.ktor.features.*
import io.ktor.gson.*


val contentNegotiationConf = fun ContentNegotiation.Configuration.() {
    gson {
    }
}
