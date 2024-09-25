package com.svmsoftware.gamesapp.presentation.wishlist

import com.svmsoftware.gamesapp.base.BaseViewModel
import com.svmsoftware.gamesapp.domain.model.Game
import com.svmsoftware.gamesapp.domain.usecase.GetAllSavedGames
import com.svmsoftware.gamesapp.domain.usecase.ToggleGameWishlist
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WishlistViewModel(
    private val getAllSavedGames: GetAllSavedGames,
    private val toggleGameWishlist: ToggleGameWishlist,
) : BaseViewModel() {
    private val _state: MutableStateFlow<WishlistState> =
        MutableStateFlow(WishlistState(loading = true))
    val gamesState: StateFlow<WishlistState> = _state

    init {
        getGames()
    }

    fun toggleGameWishList(game: Game) {
        scope.launch {
            toggleGameWishlist.toggleGameWishlist(game)

            val newList: List<Game> = _state.value.games.filter {
                it.uid != game.uid
            }

            _state.value = _state.value.copy(games = newList)
        }
    }

    private fun getGames() {
        _state.value = _state.value.copy(games = getAllSavedGames.getAllSavedGames(), loading = false)
    }
}