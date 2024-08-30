package com.example.submissioncompose.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.submissioncompose.data.GameRepository
import com.example.submissioncompose.model.Game
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class JetGamesViewModel(private val repository: GameRepository): ViewModel() {

    private val _groupGames = MutableStateFlow(
        repository.getGames()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )
    val groupGames: StateFlow<Map<Char, List<Game>>> get() = _groupGames

    private val _query = mutableStateOf("")
    val query: State<String>get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _groupGames.value = repository.searchGames(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }

    fun getGameById(gameId: Long): Game {
        return repository.getGameById(gameId)
    }

}