package com.amether.kmmpager

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.engine.darwin.*

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient = HttpClient(Darwin) {
    config(this)

    engine {
        configureRequest {

        }
    }
}

actual fun initLogger() {
    Napier.base(DebugAntilog())
}