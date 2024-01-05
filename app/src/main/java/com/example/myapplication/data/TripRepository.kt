package com.example.myapplication.data

import android.net.Uri
import kotlinx.coroutines.flow.Flow

interface TripRepository {
    fun getAll(): Flow<List<Trip>>

    fun addTrip(trip:Trip)

    fun findTrip(email:String):String

}