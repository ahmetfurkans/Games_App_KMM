package com.svmsoftware.gamesapp.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.svmsoftware.gamesapp.android.R
import com.svmsoftware.gamesapp.android.screens.components.ErrorMessage
import com.svmsoftware.gamesapp.android.screens.components.Loader
import com.svmsoftware.gamesapp.android.screens.components.PlatformsTabRow
import com.svmsoftware.gamesapp.android.screens.components.SearchTextField
import com.svmsoftware.gamesapp.domain.model.Game
import com.svmsoftware.gamesapp.presentation.games.GamesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun GamesScreen(
    gamesViewModel: GamesViewModel = getViewModel(),
    onGameClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val gamesState = gamesViewModel.gamesState.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier.padding(16.dp)
    ) {
        SearchTextField(
            text = gamesState.value.query,
            onValueChange = { gamesViewModel.onQueryChange(it) },
            onSearch = { gamesViewModel.onSearchClick() },
            onFocusChanged = {},
            shouldShowHint = gamesState.value.query.isEmpty()
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (gamesState.value.platforms.isNotEmpty()) PlatformsTabRow(platforms = gamesState.value.platforms,
            selectedPlatformIndex = gamesState.value.selectedPlatformIndex,
            onSelectTab = { gamesViewModel.onSelectTab(it) })
        Spacer(modifier = Modifier.height(16.dp))
        if (gamesState.value.loading) Loader()
        if (gamesState.value.error != null) ErrorMessage(message = gamesState.value.error!!)
        if (gamesState.value.games.isNotEmpty()) GamesListView(
            gameList = gamesState.value.games, onGameClicked = onGameClicked, onWishListClicked = {
                gamesViewModel.toggleGameWishList(it)
            }
        )
    }
}

@Composable
fun GamesListView(
    gameList: List<Game>,
    onGameClicked: (Int) -> Unit,
    onWishListClicked: (Game) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(gameList) { game ->
            GameItemView(game = game, onGameClicked = onGameClicked, onWishListClicked = onWishListClicked)
        }
    }
}

@Composable
fun GameItemView(game: Game, onGameClicked: (Int) -> Unit, onWishListClicked: (Game) -> Unit) {
    val background = if(game.isWishlist) Color(0xFF5ABB3E) else  MaterialTheme.colorScheme.primary

    Column(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(8.dp))
        .clickable { onGameClicked(game.uid) }
        .background(MaterialTheme.colorScheme.primary)) {
        Box(modifier = Modifier.height(150.dp)) {
            AsyncImage(
                model = game.backgroundImg,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd) // Sağ üst köşe
                    .padding(12.dp) // Kenarlardan boşluk bırakmak için
                    .size(24.dp) // Simgenin boyutu
                    .background(background, RoundedCornerShape(4.dp))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.gift),
                    contentDescription = "Wishlist Icon",
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable { onWishListClicked(game) }
                )
            }
        }
        Text(
            text = game.name, style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 16.sp),
            modifier = Modifier.padding(8.dp),
            minLines = 2,
            maxLines = 2,
        )
    }
}