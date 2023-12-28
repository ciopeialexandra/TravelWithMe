package com.example.myapplication.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.components.NormalTextComponent
import com.example.myapplication.data.LoginViewModel

@Composable
fun ProfileScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
            .background(color = Color.White)
    )
    {
        Column(modifier = Modifier.fillMaxSize()) {
            NormalTextComponent(nameUser)
        }
    }
}



