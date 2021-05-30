package `in`.francl.bassoon.bootloader.features

import io.ktor.features.*


val defaultHeadersConf = fun DefaultHeaders.Configuration.() {
    header("X-Engine", "Ktor") // will send this header with each response
}




