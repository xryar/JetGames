package com.example.submissioncompose.screen.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.submissioncompose.R
import com.example.submissioncompose.data.GameRepository
import com.example.submissioncompose.navigation.Screen
import com.example.submissioncompose.ui.theme.SubmissionComposeTheme
import com.example.submissioncompose.viewmodel.JetGamesViewModel
import com.example.submissioncompose.viewmodel.factory.JetGamesViewModelFactory

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateToDetail: (Long) -> Unit
) {
    SubmissionComposeTheme {
        HomeContent(
            modifier = modifier,
            navController = navController,
        ) { gameId ->
            navigateToDetail(gameId)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    modifier: Modifier,
    navController: NavController,
    viewModel: JetGamesViewModel = viewModel(
        factory = JetGamesViewModelFactory(GameRepository())
    ),
    navigateToDetail: (Long) -> Unit
) {
    val groupedGames by viewModel.groupGames.collectAsState()
    val query by viewModel.query

    Box(modifier = modifier){
        LazyColumn {
            item {
                SearchBar(
                    query = query,
                    onQueryChange = viewModel::search,
                    navController = navController,
                    modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)
                )
            }
            groupedGames.forEach{ (initial, games) ->
                stickyHeader {
                    CharacterHeader(initial)
                }
                items(games, key = { it.id }) { game ->
                    GamesListItem(
                        name = game.name,
                        photoUrl = game.photoUrl,
                        description = game.description,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItemPlacement(tween(durationMillis = 100)),
                        onClick = { navigateToDetail(game.id) }
                    )
                }
            }

        }
    }
}

@Composable
fun GamesListItem(
    name: String,
    photoUrl: String,
    description: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable(onClick = onClick)
        ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(60.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = name,
                fontSize = 24.sp,
                maxLines = 1,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = description,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        androidx.compose.material3.SearchBar(
            query = query,
            onQueryChange = onQueryChange,
            onSearch = { },
            active = false,
            onActiveChange = { },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            placeholder = {
                Text(stringResource(R.string.search_game))
            },
            shape = MaterialTheme.shapes.large,
            modifier = modifier
                .weight(1f)
                .heightIn(min = 48.dp)
        ){

        }
        Spacer(modifier = modifier.width(8.dp))
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(45.dp)
                .clickable {
                    navController.navigate(Screen.Profile.route)
                }
        )
    }
}

@Composable
fun CharacterHeader(
    char: Char,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = modifier
    ) {
        Text(
            text = char.toString(),
            fontWeight = FontWeight.Black,
            color = Color.White,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp)
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun HomeScreenPreview() {
    val navController = rememberNavController()
    SubmissionComposeTheme {
        HomeScreen(
            navController = navController
        ) {

        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun GameListItemPreview() {
    SubmissionComposeTheme {
        GamesListItem(
            name = "Baldur Gate",
            description = "Lorep Ipsum acumalaka",
            photoUrl = "",
            onClick = { }
        )
    }
}