package com.example.codelab_androidroom.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class Word(@PrimaryKey(autoGenerate = true) val id:Int,
                @ColumnInfo(name = "word") val word: String)
