package com.example.myapplication.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.rules.Validator
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.TravelAppNavigate
import kotlinx.coroutines.flow.Flow

class TripViewModel(private val tripRepository: TripRepository): ViewModel() {
    private val TAG = TripViewModel::class.simpleName
    var addTripUIState = mutableStateOf(TripUIState())
    var allValidationPassed = mutableStateOf(false)
    val tripListState: Flow<List<Trip>> by lazy {  tripRepository.getAll() }

    fun findTrip(email: String) {
        tripRepository.findTrip(email)
    }
    fun addTrip(email: String,country: String,description: String,city: String,attractions: String,restaurants: String) {
        tripRepository.addTrip(Trip(email,country,description,city,attractions,restaurants))
    }
    fun onEvent(event : TripUIEvent) {
        validateDataWithRules()
        when (event) {

            is TripUIEvent.CountryChanged -> {
                addTripUIState.value = addTripUIState.value.copy(
                    country = event.country
                )
            }
            is TripUIEvent.DescriptionChanged -> {
                addTripUIState.value = addTripUIState.value.copy(
                    description = event.description
                )
            }
            is TripUIEvent.CityChanged -> {
                addTripUIState.value = addTripUIState.value.copy(
                    city = event.city
                )
            }
            is TripUIEvent.AttractionsChanged -> {
                addTripUIState.value = addTripUIState.value.copy(
                    attractions = event.attractions
                )
            }
            is TripUIEvent.RestaurantsChanged -> {
                addTripUIState.value = addTripUIState.value.copy(
                    restaurants = event.restaurants
                )
            }

            is TripUIEvent.AddTripButtonClicked -> {
                TravelAppNavigate.navigateTo(Screen.ExploreScreen)
            }
        }
    }


    private fun validateDataWithRules() {
        val countryResult = Validator.validateCountry(
            countryV = addTripUIState.value.country
        )
        val descriptionResult = Validator.validateDescription(
            descriptionV = addTripUIState.value.description
        )
        val cityResult = Validator.validateCity(
            cityV = addTripUIState.value.city
        )
        val attractionsResult = Validator.validateAttractions(
            attractionsV = addTripUIState.value.attractions
        )
        val restaurantsResult = Validator.validateRestaurants(
            restaurantsV = addTripUIState.value.restaurants
        )
        addTripUIState.value = addTripUIState.value.copy(
            countryError = countryResult.status,
            descriptionError = descriptionResult.status ,
            cityError = cityResult.status,
            attractionsError = attractionsResult.status,
            restaurantsError = restaurantsResult.status
        )
        allValidationPassed.value =  countryResult.status && descriptionResult.status && cityResult.status && attractionsResult.status && restaurantsResult.status
    }
}