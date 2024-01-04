package com.example.myapplication.data

import android.net.Uri
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class TripUIState(
    var email: String = "",
    var country: String = "",
    var description: String = "",

    var city: String = "",
    var attractions: String = "",
    var restaurants: String = "",
    var images: String = "",
    val selectedTrip: Trip? = null,
    val allTrips: Flow<List<Trip>?> = flowOf(null),

    var countryError: Boolean = false,
    var descriptionError: Boolean = false,
    var cityError: Boolean = false,
    var attractionsError: Boolean = false,
    var restaurantsError: Boolean = false
)