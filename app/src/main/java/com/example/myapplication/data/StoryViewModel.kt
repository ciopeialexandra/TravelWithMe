package com.example.myapplication.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow


class StoryViewModel(private val storyRepository: StoryRepository): ViewModel() {
    private val TAG = StoryViewModel::class.simpleName
    private var allValidationPassed = mutableStateOf(false)
    val storyListState: Flow<List<StoryUser>> by lazy { storyRepository.getAll() }
    var storyUIState = mutableStateOf(StoryUIState())


    fun findStory(email: String) {
        storyRepository.findStory(email)
    }

    fun addStory(
        email: String,
        story: String
    ) {
        storyRepository.addStory(
            StoryUser(
                email,
                story
            )
        )
    }
    fun onEvent(event: StoryUIEvent) {
        when (event) {
            is StoryUIEvent.StoryChanged -> {
                storyUIState.value = storyUIState.value.copy(
                    story = event.story
                )
            }
        }

    }
}