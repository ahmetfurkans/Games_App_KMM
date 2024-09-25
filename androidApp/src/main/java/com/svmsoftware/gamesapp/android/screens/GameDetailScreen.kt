package com.svmsoftware.gamesapp.android.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.svmsoftware.gamesapp.android.screens.components.ErrorMessage
import com.svmsoftware.gamesapp.android.screens.components.Loader
import com.svmsoftware.gamesapp.domain.model.GameDetail
import com.svmsoftware.gamesapp.presentation.game.GameDetailViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun GameDetailScreen(
    gameId: Int? = null,
    viewModel: GameDetailViewModel = getViewModel(),
    modifier: Modifier = Modifier,
) {
    val gameState = viewModel.gameState.collectAsState()
    val scrollState = rememberScrollState()

    LaunchedEffect(true) {
        gameId?.let { viewModel.getGameDetail(it) }
    }

    Column(
        modifier.verticalScroll(scrollState)
    ) {
        if (gameState.value.loading) Loader()
        if (gameState.value.error != null) ErrorMessage(message = gameState.value.error!!)
        if (gameState.value.game != null) {
            GameCard(gameDetail = gameState.value.game!!)
        }
    }
}

@Composable
fun GameCard(gameDetail: GameDetail) {

    Column {
        AsyncImage(
            model = gameDetail.backgroundImg, contentDescription = null,
            modifier = Modifier.height(200.dp),
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = gameDetail.name,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    maxLines = 1,
                    style = MaterialTheme.typography.headlineSmall
                )
                gameDetail.metacritic?.let { metacritic ->
                    Text(
                        text = metacritic,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Gray,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            gameDetail.description?.let {
                DescriptionSection(it)
            }
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Information",
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    style = MaterialTheme.typography.titleSmall
                )
                gameDetail.released?.let {
                    InformationRow("Released Date", it)
                }
                if (gameDetail.genres.isNotEmpty()) {
                    val genresText = gameDetail.genres.joinToString(separator = ", ")
                    InformationRow("Genres", genresText)
                }
                gameDetail.playtime?.let {
                    InformationRow("Play Time", it)
                }
                if (gameDetail.publishers.isNotEmpty()) {
                    val publishers = gameDetail.publishers.joinToString(separator = ", ")
                    InformationRow("Publishers", publishers)
                }
            }
            gameDetail.redditUrl?.let {
                UrlButton("Visit Reddit", it)
            }

            gameDetail.webSiteUrl?.let {
                UrlButton("Visit Website", it)
            }
        }
    }
}

@Composable
fun DescriptionSection(text: String) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .clickable { isExpanded = !isExpanded }
            .padding(16.dp)
    ) {
        Text(
            text = "Description",
            fontWeight = FontWeight.Medium,
            color = Color.White,
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = text,
            fontWeight = FontWeight.Medium,
            color = Color.Gray,
            maxLines = if (isExpanded) Int.MAX_VALUE else 4, // Show full text if expanded
            modifier = Modifier
                .clickable { isExpanded = !isExpanded } // Expand/Collapse on click
                .animateContentSize() // Animate transition
        )
    }
}

@Composable
fun InformationRow(title: String, content: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Medium,
            color = Color.Gray,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = content,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun UrlButton(title: String, websiteLink: String) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(websiteLink))
                context.startActivity(intent)
            }.padding(16.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = Color.Gray
        )
    }
}
