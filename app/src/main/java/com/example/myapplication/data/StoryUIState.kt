package com.example.myapplication.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class StoryUIState(
    var email: String = "",
    var story: String = "",
    val allTrips: Flow<List<Trip>?> = flowOf(null),
)