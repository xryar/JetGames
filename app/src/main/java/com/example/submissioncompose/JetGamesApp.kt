package com.example.submissioncompose

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.example.submissioncompose.data.GameRepository
import com.example.submissioncompose.model.GameData
import com.example.submissioncompose.navigation.Screen
import com.example.submissioncompose.screen.detail.DetailScreen
import com.example.submissioncompose.screen.home.HomeScreen
import com.example.submissioncompose.ui.theme.SubmissionComposeTheme
import com.example.submissioncompose.viewmodel.JetGamesViewModel
import com.example.submissioncompose.viewmodel.factory.JetGamesViewModelFactory

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
                navigateToDetail = { gameId ->
                    navController.navigate(Screen.DetailGame.createRoute(gameId))
                }
            )
        }
        composable(
            route = Screen.DetailGame.route,
            arguments = listOf(navArgument("gameId"){ type = NavType.LongType } )
        ) {
            val id = it.arguments?.getLong("gameId") ?: -1L
            DetailScreen(
                gameId = id
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