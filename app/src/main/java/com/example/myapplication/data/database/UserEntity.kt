package com.example.myapplication.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    val email: String,

    @ColumnInfo(name = "firstName")
    val firstName: String,

    @ColumnInfo(name = "lastName")
    val lastName: String
)