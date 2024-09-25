package com.svmsoftware.gamesapp.presentation.game

import com.svmsoftware.gamesapp.base.BaseViewModel
import com.svmsoftware.gamesapp.domain.usecase.GameDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameDetailViewModel(
    private val gameDetailUseCase: GameDetailUseCase,
) : BaseViewModel() {
    private val _state: MutableStateFlow<GameDetailState> = MutableStateFlow(GameDetailState(loading = true))
    val gameState: StateFlow<GameDetailState> = _state

    fun getGameDetail(uid: Int) {
        scope.launch {
            _state.value = _state.value.copy(loading = true)
            val gameDetail = gameDetailUseCase.getGameDetail(uid)
            _state.value = _state.value.copy(loading = false, game = gameDetail)
        }
    }
}