package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.components.CameraGalleryChooser
import com.example.myapplication.data.LoginViewModel
import com.example.myapplication.data.TripViewModel

@Composable
fun StoryScreen(loginViewModel: LoginViewModel) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
            .background(color = Color.Black)
    ) {
        CameraGalleryChooser(loginViewModel)
    }
}