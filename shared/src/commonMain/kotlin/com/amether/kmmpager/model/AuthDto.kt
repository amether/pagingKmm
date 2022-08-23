package com.amether.kmmpager.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthDto(
    @SerialName("id") val id: Int,
    @SerialName("firstName") val firstName: String,
    @SerialName("lastName") val lastName: String,
    @SerialName("username") val username: String,
    @SerialName("onboard") val onboard: Int,
    @SerialName("createdAt") val createdAt: String,
    @SerialName("updatedAt") val updatedAt: String,
    @SerialName("token") val token: String,
) {

    override fun toString(): String {
        return StringBuilder()
            .append("id: $id\n")
            .append("first name: $firstName\n")
            .append("last name: $lastName\n")
            .append("username: $username\n")
            .append("onboard: $onboard\n")
            .append("created at: $createdAt\n")
            .append("updated at: $updatedAt\n")
            .append("token: $token")
            .toString()
    }
}