package com.example.myapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.Trip

@Database(entities = [Trip::class], version = 1)
abstract class TripDatabase : RoomDatabase() {
    abstract fun tripDao(): TripDao

}