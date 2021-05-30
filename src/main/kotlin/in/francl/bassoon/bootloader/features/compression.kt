package `in`.francl.bassoon.bootloader.features

import io.ktor.features.*


val compressionConf = fun Compression.Configuration.() {
    gzip {
        priority = 1.0
    }
    deflate {
        priority = 10.0
        minimumSize(1024) // condition
    }
}


