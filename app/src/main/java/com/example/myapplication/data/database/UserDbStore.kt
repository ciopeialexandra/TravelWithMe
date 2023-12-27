package com.example.myapplication.data.database

import com.example.myapplication.data.User
import com.example.myapplication.data.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class UserDbStore(private val appDatabase: AppDatabase) : UserRepository {

    override fun getAll(): Flow<List<User>> {
        return appDatabase.userDao().getAll().map { list -> list.map { it.toDomainModel() } }
    }

    override fun addUser(user: User) {
        appDatabase.userDao().insert(user.toDbModel())
    }
    override fun findUser(email: String): String {
        return appDatabase.userDao().find(email)
    }

    private fun User.toDbModel() = UserEntity(email,firstName,lastName)

    private fun UserEntity.toDomainModel() = User(email,firstName, lastName )

}