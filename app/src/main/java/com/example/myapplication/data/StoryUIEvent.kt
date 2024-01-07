package com.example.myapplication.data

sealed class StoryUIEvent {
    data class StoryChanged(val story: String) : StoryUIEvent()

}