package com.example.submissioncompose

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.submissioncompose.navigation.Screen
import com.example.submissioncompose.screen.detail.DetailScreen
import com.example.submissioncompose.screen.home.HomeScreen
import com.example.submissioncompose.screen.profile.ProfileScreen
import com.example.submissioncompose.ui.theme.SubmissionComposeTheme

@Composable
fun JetGamesApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route){
            HomeScreen(
                navController = navController,
                navigateToDetail = { gameId ->
                    navController.navigate(Screen.DetailGame.createRoute(gameId))
                }
            )
        }
        composable(Screen.Profile.route){
            ProfileScreen(
                navController = navController
            )
        }
        composable(
            route = Screen.DetailGame.route,
            arguments = listOf(navArgument("gameId"){ type = NavType.LongType } )
        ) {
            val id = it.arguments?.getLong("gameId") ?: -1L
            DetailScreen(
                gameId = id,
                navController = navController
            )
        }
    }
}


@Preview(
    showBackground = true
)
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun JetGamesAppPreview() {
    SubmissionComposeTheme {
        JetGamesApp()
    }
}