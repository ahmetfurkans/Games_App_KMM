package com.svmsoftware.gamesapp.presentation.games

import com.svmsoftware.gamesapp.base.BaseViewModel
import com.svmsoftware.gamesapp.domain.model.Game
import com.svmsoftware.gamesapp.domain.usecase.GamesUseCase
import com.svmsoftware.gamesapp.domain.usecase.PlatformsUseCase
import com.svmsoftware.gamesapp.domain.usecase.ToggleGameWishlist
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GamesViewModel(
    private val gamesUseCase: GamesUseCase,
    private val platformsUseCase: PlatformsUseCase,
    private val toggleGameWishlist: ToggleGameWishlist,
) : BaseViewModel() {
    private val _state: MutableStateFlow<GamesState> = MutableStateFlow(GamesState(loading = true))
    val gamesState: StateFlow<GamesState> = _state

    init {
        getPlatforms()
        getGames()
    }

    fun onQueryChange(query: String) {
        _state.value = _state.value.copy(query = query)
    }

    fun onSelectTab(platformIndex: Int) {
        _state.value = _state.value.copy(selectedPlatformIndex = platformIndex)
        getGames()
    }

    fun onSearchClick() {
        getGames()
    }

    fun toggleGameWishList(game: Game) {
        scope.launch {
            toggleGameWishlist.toggleGameWishlist(game)

            val newList = _state.value.games.map {
                if (it.uid == game.uid) {
                    it.copy(isWishlist = !it.isWishlist)
                } else {
                    it
                }
            }
            _state.value = _state.value.copy(games = newList)
        }
    }

    private fun getGames(query: String = _state.value.query) {
        val platform =
            if (_state.value.selectedPlatformIndex > -1) _state.value.platforms[_state.value.selectedPlatformIndex] else null

        scope.launch {
            _state.value = _state.value.copy(loading = true)
            val fetchGames = gamesUseCase.getGames(query, platform)
            _state.value = _state.value.copy(loading = false, games = fetchGames)
        }
    }

    private fun getPlatforms() {
        scope.launch {
            _state.value = _state.value.copy(loading = true)
            val fetchPlatforms = platformsUseCase.getPlatforms()
            _state.value = _state.value.copy(loading = false, platforms = fetchPlatforms)
        }
    }
}