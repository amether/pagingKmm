package com.amether.kmmpager

import io.ktor.client.*

expect fun httpClient (config: HttpClientConfig<*>.() -> Unit = {}) : HttpClient

expect fun initLogger ()