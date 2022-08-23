package com.amether.kmmpager.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    @SerialName("username") val username: String,
    @SerialName("password") val password: String,
)
