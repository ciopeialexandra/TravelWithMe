package com.example.myapplication.data.database

import com.example.myapplication.data.StoryRepository
import com.example.myapplication.data.StoryUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoryDbStore(private val storyDatabase: StoryDatabase) : StoryRepository {

    override fun getAll(): Flow<List<StoryUser>> {
        return storyDatabase.storyDao().getAll().map { list -> list.map { it.toDomainModel() } }
    }

    override fun addStory(story: StoryUser) {
        storyDatabase.storyDao().insert(story.toDbModel())
    }
    override fun findStory(email: String): String {
        return storyDatabase.storyDao().find(email)
    }

    private fun StoryUser.toDbModel() = StoryEntity(email,story)

    private fun StoryEntity.toDomainModel() = StoryUser(email,story)

}