package com.example.codelab_androidroom

import androidx.lifecycle.*
import com.example.codelab_androidroom.database.WordRepository
import com.example.codelab_androidroom.database.entities.Word
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class WordViewModel (private val repository:WordRepository) : ViewModel() {
    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()

    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }

    fun delete(word:Word) = viewModelScope.launch {
        repository.delete(word)
    }
}

class WordViewModelFactory(private val repository: WordRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WordViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}