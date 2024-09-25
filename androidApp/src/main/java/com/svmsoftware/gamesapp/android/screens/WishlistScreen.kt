package com.svmsoftware.gamesapp.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.svmsoftware.gamesapp.android.screens.components.Appbar
import com.svmsoftware.gamesapp.android.screens.components.ErrorMessage
import com.svmsoftware.gamesapp.android.screens.components.Loader
import com.svmsoftware.gamesapp.presentation.wishlist.WishlistViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun WishlistScreen(
    viewModel: WishlistViewModel = getViewModel(),
    onGameClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val gamesState = viewModel.gamesState.collectAsState()

    Column(
    ) {
        Appbar(title = "Wishlist")
        Column(
            modifier.padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            if (gamesState.value.loading) Loader()
            if (gamesState.value.error != null) ErrorMessage(message = gamesState.value.error!!)
            if (gamesState.value.games.isNotEmpty()) GamesListView(
                gameList = gamesState.value.games,
                onGameClicked = onGameClicked,
                onWishListClicked = {
                    viewModel.toggleGameWishList(it)
                }
            )
        }
    }
}
