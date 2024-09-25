package com.svmsoftware.gamesapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

actual open class BaseViewModel : ViewModel() {
    actual val scope: CoroutineScope
        get() = viewModelScope

    actual fun dispose() {
        scope.cancel()
        onCleared()
    }

    actual override fun onCleared() {
        super.onCleared()
    }
}
