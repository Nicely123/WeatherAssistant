package com.example.weatherassistant.ui.joke

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weatherassistant.database.JokeDao
import com.example.weatherassistant.domain.DisplayJoke
import com.example.weatherassistant.repository.JokeRepository
import kotlinx.coroutines.launch

class JokeViewModel(
    jokeDao: JokeDao
): ViewModel() {
    private val jokeRepository: JokeRepository = JokeRepository(jokeDao)

    var displayJokes: LiveData<List<DisplayJoke>?>  = jokeRepository.jokes

    init {
        viewModelScope.launch {
            jokeRepository.storeJokes()
        }
    }

    fun setJokes(){
        displayJokes = jokeRepository.fetchJokes()
    }
}

class JokeViewModelFactory(private val jokeDao: JokeDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(JokeViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return JokeViewModel(jokeDao) as T
        }
        throw IllegalArgumentException("Unable to construct view model")
    }
}