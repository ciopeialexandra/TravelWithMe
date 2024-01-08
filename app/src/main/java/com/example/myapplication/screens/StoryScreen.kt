package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myapplication.components.CameraGalleryChooser
import com.example.myapplication.components.showStory
import com.example.myapplication.data.StoryViewModel

@Composable
fun StoryScreen(storyViewModel: StoryViewModel) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Column {

            CameraGalleryChooser(storyViewModel)
            showStory(storyViewModel = storyViewModel)
        }
    }
}