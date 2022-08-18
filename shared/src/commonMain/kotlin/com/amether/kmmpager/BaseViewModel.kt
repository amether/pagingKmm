package com.amether.kmmpager

import kotlinx.coroutines.CoroutineScope

expect open class BaseViewModel() {

    fun clear()

    fun getViewModelScope(): CoroutineScope

    protected open fun onCleared()

}