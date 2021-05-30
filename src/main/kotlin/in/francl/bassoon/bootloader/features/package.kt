package `in`.francl.bassoon.bootloader.features

import io.ktor.application.*
import io.ktor.features.*


fun Application.installFeatures() {
    install(Compression, compressionConf)
    install(DefaultHeaders, defaultHeadersConf)
    install(ContentNegotiation, contentNegotiationConf)
}
