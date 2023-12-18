package com.example.myapplication.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.BottomNavGraph
import com.example.myapplication.components.BottomBar


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ExploreScreen(){
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
            .background(color = Color.Black)
    ){

    }
        val navController = rememberNavController()

        androidx.compose.material.Scaffold(
            bottomBar = { BottomBar(navController = navController) }
        ) {
            BottomNavGraph(navController = navController)
        }

}
