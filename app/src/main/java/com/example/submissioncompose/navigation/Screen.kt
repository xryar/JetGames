package com.example.submissioncompose.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Profile: Screen("profile")
    object DetailGame: Screen("home/{gameId}") {
        fun createRoute(gameId: Long) = "home/$gameId"
    }
}