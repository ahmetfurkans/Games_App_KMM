package com.svmsoftware.gamesapp.android.navigation

import androidx.annotation.DrawableRes
import com.svmsoftware.gamesapp.android.R

data class BottomNavItem(
    val route: String,
    @DrawableRes val painterRes: Int,
    val label: String
)

val BottomNavItems = listOf(
    BottomNavItem(Screens.GAMES.route, R.drawable.controller, Screens.GAMES.name),
    BottomNavItem(Screens.WISHLIST.route, R.drawable.gift ,Screens.WISHLIST.name)
)
