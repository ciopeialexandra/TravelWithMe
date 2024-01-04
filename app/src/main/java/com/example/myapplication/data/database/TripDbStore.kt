package com.example.myapplication.data.database

import com.example.myapplication.data.Trip
import com.example.myapplication.data.TripRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class TripDbStore(private val tripDatabase: TripDatabase) : TripRepository {

    override fun getAll(): Flow<List<Trip>> {
        return tripDatabase.tripDao().getAll().map { list -> list.map { it.toDomainModel() } }
    }

    override fun addTrip(trip: Trip) {
        tripDatabase.tripDao().insert(trip.toDbModel())
    }
    override fun findTrip(email: String): String {
        return tripDatabase.tripDao().find(email)
    }

    private fun Trip.toDbModel() = TripEntity(email,country,description,city,attractions,restaurants)

    private fun TripEntity.toDomainModel() = Trip(email,country,description,city,attractions,restaurants)

}