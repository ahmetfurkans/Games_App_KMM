package com.svmsoftware.gamesapp.base

import kotlinx.coroutines.CoroutineScope

expect open class BaseViewModel() {
    val scope: CoroutineScope

    fun dispose()

    protected open fun onCleared()
}
