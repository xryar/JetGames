package com.example.submissioncompose.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.submissioncompose.data.GameRepository
import com.example.submissioncompose.viewmodel.JetGamesViewModel

class JetGamesViewModelFactory(private val repository: GameRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCKECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JetGamesViewModel::class.java)){
            return JetGamesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

}