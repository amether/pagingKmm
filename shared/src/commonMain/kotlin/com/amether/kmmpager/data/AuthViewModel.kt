package com.amether.kmmpager.data

import com.amether.kmmpager.domain.AuthRepo
import com.amether.kmmpager.model.AuthDto
import com.amether.kmmpager.model.Response
import io.ktor.client.call.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepo: AuthRepo,
) {

    private val _dataFlow = MutableStateFlow<AuthDto?>(null)
    val dataFlow: StateFlow<AuthDto?> = _dataFlow.asStateFlow()

    private val _errorFlow = MutableSharedFlow<Response.Failed>()
    val errorFlow: SharedFlow<Response.Failed> = _errorFlow.asSharedFlow()

    fun auth(isSuccess: Boolean) {
        MainScope().launch {
            val username = if(isSuccess) TRUE_USERNAME else FALSE_USERNAME
            val response = authRepo.auth(username, PASSWORD)
            if (response.status.value in 200..299) {
                _dataFlow.value = response.body()
            } else {
                _errorFlow.emit(Response.Failed(response.body(), response.status.value))
            }
        }
    }

    companion object {
        const val TRUE_USERNAME = "a.pyanov"
        const val FALSE_USERNAME = "1"
        const val PASSWORD = "AeTae2oogeekohphephi"
    }
}