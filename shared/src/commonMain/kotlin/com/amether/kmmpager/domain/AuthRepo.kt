package com.amether.kmmpager.domain

import com.amether.kmmpager.data.HttpClientFactory
import com.amether.kmmpager.model.Response

class AuthRepo(private val httpClientFactory: HttpClientFactory) {

    suspend fun auth(username: String, password: String): Response {
        return httpClientFactory.postOrError(AUTH_URL, username, password)
    }

    companion object {
        private const val AUTH_URL = "https://trullo.globus-ltd.com/api/users/authenticate"
    }
}