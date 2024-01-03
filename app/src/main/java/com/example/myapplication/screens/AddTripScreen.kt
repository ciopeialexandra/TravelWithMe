package com.example.myapplication.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.components.ButtonComponent
import com.example.myapplication.components.CenteredInRowTextField
import com.example.myapplication.components.HeadingTextComponent
import com.example.myapplication.components.LeftTextComponent
import com.example.myapplication.data.TripUIEvent
import com.example.myapplication.data.TripViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun AddTripScreen(tripViewModel: TripViewModel = viewModel()){
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

        ) {
                HeadingTextComponent(value = "Add trip")
                LeftTextComponent(value = "Country")
                CenteredInRowTextField(
                    onTextSelected ={
                        tripViewModel.onEvent(TripUIEvent.CountryChanged(it))
                })
                LeftTextComponent(value = "City")
                CenteredInRowTextField(
                    onTextSelected ={
                        tripViewModel.onEvent(TripUIEvent.CityChanged(it))
                    }
                )
                LeftTextComponent(value = "Description")
                CenteredInRowTextField(
                    onTextSelected ={
                        tripViewModel.onEvent(TripUIEvent.DescriptionChanged(it))
                    }
                )
                LeftTextComponent(value = "Attractions")
                CenteredInRowTextField(
                    onTextSelected ={
                        tripViewModel.onEvent(TripUIEvent.AttractionsChanged(it))
                    }
                )
                LeftTextComponent(value = "Restaurants")
                CenteredInRowTextField(
                    onTextSelected ={
                        tripViewModel.onEvent(TripUIEvent.RestaurantsChanged(it))
                    }
                )
                LeftTextComponent(value = "Photos")
//                AddPhotosFromGallery()
                Spacer(modifier = Modifier.height(40.dp))
                ButtonComponent(value = stringResource(id = R.string.save),
                    onButtonClicked = {
                        tripViewModel.onEvent(TripUIEvent.AddTripButtonClicked)
                        tripViewModel.addTrip(tripViewModel.addTripUIState.value.email,tripViewModel.addTripUIState.value.country,tripViewModel.addTripUIState.value.description,tripViewModel.addTripUIState.value.city,tripViewModel.addTripUIState.value.attractions,tripViewModel.addTripUIState.value.restaurants)
                    },
                    isEnabled = true
                )
        }

    }
}
