package com.example.myapplication.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "story")
data class StoryEntity (
        @PrimaryKey
        val email: String,

        @ColumnInfo(name = "story")
        val story: String
    )