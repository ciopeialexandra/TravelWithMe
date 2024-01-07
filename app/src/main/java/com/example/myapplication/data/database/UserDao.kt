package com.example.myapplication.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<UserEntity>>

    @Insert
    fun insert(user: UserEntity)


    @Query("SELECT * FROM user WHERE email LIKE :emailFun LIMIT 1")
    fun find(emailFun: String) :String



}