package com.example.myapplication.data

import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getAll(): Flow<List<User>>

    fun addUser(user:User)

    fun findUser(email:String):String

}