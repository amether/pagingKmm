package com.amether.kmmpager

import kotlinx.coroutines.CoroutineScope

actual open class BaseViewModel actual constructor() {

    actual fun clear() {
    }

    actual fun getViewModelScope(): CoroutineScope {
        TODO("Not yet implemented")
    }

    protected actual open fun onCleared() {
    }

}