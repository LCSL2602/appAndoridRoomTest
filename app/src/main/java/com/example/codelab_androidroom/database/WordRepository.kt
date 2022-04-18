package com.example.codelab_androidroom.database

import androidx.annotation.WorkerThread
import com.example.codelab_androidroom.database.dao.WordDao
import com.example.codelab_androidroom.database.entities.Word
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao:WordDao) {
    val allWords:Flow<List<Word>> = wordDao.getAlphabetizedWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    suspend fun delete(word: Word) {
        wordDao.deleteWord(word)
    }
}