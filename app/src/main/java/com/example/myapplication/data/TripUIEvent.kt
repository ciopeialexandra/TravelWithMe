package com.example.myapplication.data


sealed class TripUIEvent {
    data class CountryChanged(val country: String) : TripUIEvent()
    data class DescriptionChanged(val description: String) : TripUIEvent()
    data class CityChanged(val city: String) : TripUIEvent()
    data class AttractionsChanged(val attractions: String) : TripUIEvent()
    data class RestaurantsChanged(val restaurants: String) : TripUIEvent()
    data class PhotoAdded(val images: String):TripUIEvent()
    data class TripClicked(val trip: Trip) : TripUIEvent()
    object BackButtonClicked : TripUIEvent()

    object AddTripButtonClicked : TripUIEvent()
}