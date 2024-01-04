package com.example.myapplication.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TripDao {
    @Query("SELECT * FROM trip")
    fun getAll(): Flow<List<TripEntity>>

    @Insert
    fun insert(trip: TripEntity)

    @Query("SELECT * FROM trip WHERE email LIKE :emailFun LIMIT 1")
    fun find(emailFun: String) :String


}