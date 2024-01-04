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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.components.ButtonComponent
import com.example.myapplication.components.HeadingTextComponent
import com.example.myapplication.components.LeftTextComponent
import com.example.myapplication.components.MyTextFieldComponent
import com.example.myapplication.components.NormalTextComponent
import com.example.myapplication.components.ShowImage
import com.example.myapplication.data.TripUIEvent
import com.example.myapplication.data.TripViewModel
import com.example.myapplication.data.firebase.emailDb

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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
            Spacer(modifier = Modifier.height(80.dp))
            MyTextFieldComponent(labelValue = stringResource(id = R.string.country),
                    onTextSelected ={
                        tripViewModel.onEvent(TripUIEvent.CountryChanged(it))
                },
                errorStatus = tripViewModel.addTripUIState.value.countryError
                )
            MyTextFieldComponent(labelValue = stringResource(id = R.string.city),
                    onTextSelected ={
                        tripViewModel.onEvent(TripUIEvent.CityChanged(it))
                    },
                    errorStatus = tripViewModel.addTripUIState.value.cityError
                )
            MyTextFieldComponent(labelValue = stringResource(id = R.string.description),
                    onTextSelected ={
                        tripViewModel.onEvent(TripUIEvent.DescriptionChanged(it))
                    },
                errorStatus = tripViewModel.addTripUIState.value.descriptionError
                )
            MyTextFieldComponent(labelValue = stringResource(id = R.string.attractions),
                    onTextSelected ={
                        tripViewModel.onEvent(TripUIEvent.AttractionsChanged(it))
                    },
                errorStatus = tripViewModel.addTripUIState.value.attractionsError
                )
            MyTextFieldComponent(labelValue = stringResource(id = R.string.restaurnats),
                    onTextSelected ={
                        tripViewModel.onEvent(TripUIEvent.RestaurantsChanged(it))
                    },
                    errorStatus = tripViewModel.addTripUIState.value.restaurantsError
                )

                Spacer(modifier = Modifier.height(20.dp))
                NormalTextComponent(value = "Photos","Left")
                ShowImage()
                ButtonComponent(value = stringResource(id = R.string.save),
                    onButtonClicked = {
                        tripViewModel.onEvent(TripUIEvent.AddTripButtonClicked)
                        tripViewModel.addTrip(emailDb,tripViewModel.addTripUIState.value.country,tripViewModel.addTripUIState.value.description,tripViewModel.addTripUIState.value.city,tripViewModel.addTripUIState.value.attractions,tripViewModel.addTripUIState.value.restaurants,tripViewModel.addTripUIState.value.images)
                    },
                    isEnabled = true
                )
        }
    }
}
