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
import com.example.myapplication.components.MyTextFieldComponent
import com.example.myapplication.data.TripUIEvent
import com.example.myapplication.data.TripViewModel
import com.example.myapplication.data.firebase.emailDb

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
                .background(color = Color.White)
                .padding(15.dp)

        ) {
                HeadingTextComponent(value = "Add trip")
                LeftTextComponent(value = "Country")
            MyTextFieldComponent(
                    onTextSelected ={
                        tripViewModel.onEvent(TripUIEvent.CountryChanged(it))
                },
                errorStatus = tripViewModel.addTripUIState.value.countryError
                )
                LeftTextComponent(value = "City")
            MyTextFieldComponent(
                    onTextSelected ={
                        tripViewModel.onEvent(TripUIEvent.CityChanged(it))
                    },
                    errorStatus = tripViewModel.addTripUIState.value.cityError
                )
                LeftTextComponent(value = "Description")
            MyTextFieldComponent(
                    onTextSelected ={
                        tripViewModel.onEvent(TripUIEvent.DescriptionChanged(it))
                    },
                errorStatus = tripViewModel.addTripUIState.value.descriptionError
                )
                LeftTextComponent(value = "Attractions")
            MyTextFieldComponent(
                    onTextSelected ={
                        tripViewModel.onEvent(TripUIEvent.AttractionsChanged(it))
                    },
                errorStatus = tripViewModel.addTripUIState.value.attractionsError
                )
                LeftTextComponent(value = "Restaurants")
            MyTextFieldComponent(
                    onTextSelected ={
                        tripViewModel.onEvent(TripUIEvent.RestaurantsChanged(it))
                    },
                    errorStatus = tripViewModel.addTripUIState.value.restaurantsError
                )
                LeftTextComponent(value = "Photos")
//                AddPhotosFromGallery()
                Spacer(modifier = Modifier.height(40.dp))
                ButtonComponent(value = stringResource(id = R.string.save),
                    onButtonClicked = {
                        tripViewModel.onEvent(TripUIEvent.AddTripButtonClicked)
                        tripViewModel.addTrip(emailDb,tripViewModel.addTripUIState.value.country,tripViewModel.addTripUIState.value.description,tripViewModel.addTripUIState.value.city,tripViewModel.addTripUIState.value.attractions,tripViewModel.addTripUIState.value.restaurants)
                    },
                    isEnabled = true
                )
        }

    }
}
