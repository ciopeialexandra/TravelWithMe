package com.example.myapplication.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trip")
data class TripEntity(
    @PrimaryKey
    val email: String,

    @ColumnInfo(name = "country")
    val country: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "attractions")
    val attractions: String,

    @ColumnInfo(name = "restaurants")
    val restaurants: String,

    @ColumnInfo(name = "images")
    val images: String
)