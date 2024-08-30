package com.example.submissioncompose.data

import com.example.submissioncompose.model.Game
import com.example.submissioncompose.model.GameData

class GameRepository {

    fun getGames(): List<Game> {
        return GameData.games
    }

    fun searchGames(query: String): List<Game>{
        return GameData.games.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

}