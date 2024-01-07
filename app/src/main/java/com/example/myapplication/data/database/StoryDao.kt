package com.example.myapplication.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StoryDao {
    @Query("SELECT * FROM story")
    fun getAll(): Flow<List<StoryEntity>>

    @Insert
    fun insert(story: StoryEntity)


    @Query("SELECT * FROM story WHERE email LIKE :emailFun LIMIT 1")
    fun find(emailFun: String) :String


}