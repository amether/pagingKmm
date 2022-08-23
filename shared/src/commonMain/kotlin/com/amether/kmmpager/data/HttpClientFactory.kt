package com.amether.kmmpager.data

import com.amether.kmmpager.initLogger
import com.amether.kmmpager.model.AuthRequest
import com.amether.kmmpager.model.Response
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object HttpClientFactory {
    private const val TAG = "HTTP client"

    val httpClient = HttpClient() {
        install(Logging) {
            level = LogLevel.HEADERS
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.log(priority = Napier.Level.VERBOSE, tag = TAG, message = message)
                }
            }
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }.also {
        initLogger()
    }

    suspend inline fun <reified T> get(url: String): T = httpClient.get(url).body()

    suspend fun postOrError(url: String, username: String, password: String): Response {
        val request = httpClient.post(url) {
            contentType(ContentType.Application.Json)
            setBody(AuthRequest(username, password))
        }
        return if (request.status.value in 200..299) {
            Response.Success(request.body())
        } else {
            Response.Failed(request.body(), request.status.value)
        }
    }

}