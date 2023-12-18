package com.example.myapplication.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.components.AddPhotosFromGallery
import com.example.myapplication.components.CenteredInRowTextField
import com.example.myapplication.components.HeadingWhiteTextComponent
import com.example.myapplication.components.LeftTextComponent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
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
                .background(color = Color.DarkGray)
                .padding(15.dp)
                .verticalScroll(rememberScrollState())
                //.weight(1f, false)
        ) {
                HeadingWhiteTextComponent(value = "Add trip")
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
