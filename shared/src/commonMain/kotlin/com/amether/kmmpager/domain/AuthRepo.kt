package com.amether.kmmpager.domain

import com.amether.kmmpager.data.HttpClientFactory
import com.amether.kmmpager.model.AuthRequest
import com.amether.kmmpager.model.Response
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class AuthRepo(private val httpClientFactory: HttpClientFactory) {

    suspend fun auth(username: String, password: String): HttpResponse {
        return httpClientFactory.httpClient.post(AUTH_URL) {
            contentType(ContentType.Application.Json)
            setBody(AuthRequest(username, password))
        }
    }

    companion object {
        private const val AUTH_URL = "https://trullo.globus-ltd.com/api/users/authenticate"
    }
}