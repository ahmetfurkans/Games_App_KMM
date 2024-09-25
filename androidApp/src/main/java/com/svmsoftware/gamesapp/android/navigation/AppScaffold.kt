package com.svmsoftware.gamesapp.android.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.svmsoftware.gamesapp.android.screens.GameDetailScreen
import com.svmsoftware.gamesapp.android.screens.GamesScreen
import com.svmsoftware.gamesapp.android.screens.WishlistScreen
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild

@Composable
fun AppScaffold() {
    val navController = rememberNavController()
    val hazeState = remember { HazeState() }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                modifier = Modifier
                    .hazeChild(state = hazeState)
            )
        }
    ) {
        AppNavHost(
            navController = navController,
            modifier = Modifier
                .haze(
                    hazeState,
                    style = HazeDefaults.style(backgroundColor = MaterialTheme.colorScheme.primary),
                )
                .fillMaxSize()
                .padding(it)
        )
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screens.GAMES.route,
        modifier = modifier,
    ) {
        composable(Screens.GAMES.route) {
            GamesScreen(
                onGameClicked = { navController.navigate("${Screens.GAME_DETAILS.route}/${it}") },
            )
        }
        composable(Screens.WISHLIST.route) {
            WishlistScreen(
                onGameClicked = { navController.navigate("${Screens.GAME_DETAILS.route}/${it}") })
        }
        composable(
            "${Screens.GAME_DETAILS.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            GameDetailScreen(
                gameId = it.arguments!!.getInt("id"),
            )
        }
    }
}
