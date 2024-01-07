package com.example.myapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.StoryUser

@Database(entities = [StoryUser::class], version = 1)
abstract class StoryDatabase : RoomDatabase() {
    abstract fun storyDao(): StoryDao
}