package com.amether.kmmpager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

actual open class BaseViewModel: ViewModel() {

    actual fun clear() {
        onCleared()
    }

    actual fun getViewModelScope(): CoroutineScope {
        return viewModelScope
    }

    actual override fun onCleared() {
        super.onCleared()
    }
}