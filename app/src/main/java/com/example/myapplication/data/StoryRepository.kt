package com.example.myapplication.data

import kotlinx.coroutines.flow.Flow

interface StoryRepository {
    fun getAll(): Flow<List<StoryUser>>

    fun addStory(story:StoryUser)

    fun findStory(email:String):String

}