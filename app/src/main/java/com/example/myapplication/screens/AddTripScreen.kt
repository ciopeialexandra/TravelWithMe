package com.example.myapplication.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.components.AddPhotosFromGallery
import com.example.myapplication.components.CenteredInRowTextField
import com.example.myapplication.components.HeadingTextComponent
import com.example.myapplication.components.LeftTextComponent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddTripScreen(){
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(color = Color.White)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            HeadingTextComponent(value = "Add trip")
            LeftTextComponent(value = "Country")
            CenteredInRowTextField()
            LeftTextComponent(value = "City")
            CenteredInRowTextField()
            LeftTextComponent(value = "Description")
            CenteredInRowTextField()
            LeftTextComponent(value = "Attractions")
            CenteredInRowTextField()
            LeftTextComponent(value = "Restaurants")
            CenteredInRowTextField()
            LeftTextComponent(value = "Photos")
            AddPhotosFromGallery()
        }

    }

}