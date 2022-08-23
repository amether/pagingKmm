package com.amether.kmmpager.model

sealed interface Response {

    data class Failed(val error: ErrorResponseDto, val errorCode: Int): Response
    data class Success(val authDto: AuthDto): Response
}