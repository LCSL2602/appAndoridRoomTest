package com.example.codelab_androidroom

import android.app.Application
import com.example.codelab_androidroom.database.WordRepository
import com.example.codelab_androidroom.database.WordRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication: Application() {
    private val scopeApplication = CoroutineScope(SupervisorJob())

    val database by lazy { WordRoomDatabase.getDataBase(this,scopeApplication) }
    val repository by lazy { WordRepository(database.wordDao()) }
}