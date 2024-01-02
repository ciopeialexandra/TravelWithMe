package com.example.myapplication.data.firebase

import android.util.Log
import com.example.myapplication.data.User
import com.example.myapplication.data.UserRepository
import com.example.myapplication.screens.nameUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

var emailUser = ""
class FirebaseUserStore : UserRepository {

    private val database = FirebaseDatabase.getInstance().reference.child("user")

    override fun getAll(): Flow<List<User>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.e("FirebaseUserStore", "getAll:", p0.toException())
            }

            override fun onDataChange(p0: DataSnapshot) {
                val nodeValues = mutableListOf<UserNode>()

                val children = p0.children
                // TODO 15: Iterate through the children, get the node value and
                //  add it to nodeValues.
                for (child in children) {
                    val userNode = child.getValue(UserNode::class.java)
                    userNode?.let{nodeValues.add(userNode)}
                }
                val items = nodeValues.map { userNode -> userNode.toDomainModel() }

                trySend(items)
            }

        }
        database.addListenerForSingleValueEvent(listener)

        awaitClose { database.removeEventListener(listener) }
    }

    override fun addUser(user: User) {
         database.push().setValue(user.toFirebaseModel())
    }
    override fun findUser(email: String):String {
        var fullname = "";
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.e("FirebaseUserStore", "getAll:", p0.toException())
            }

            override fun onDataChange(p0: DataSnapshot) {

                val children = p0.children
                for (child in children) {
                    if (child.getValue(UserNode::class.java)?.email.equals(email)) {
                       fullname =
                            child.getValue(UserNode::class.java)?.firstName + " " +child.getValue(
                                UserNode::class.java
                            )?.lastName
                        emailUser = email
                    }
                }
                nameUser = fullname
            }
        }
        database.addListenerForSingleValueEvent(listener)

        database.removeEventListener(listener)

        return fullname.ifEmpty {
            "Name not found"
        }

    }


    fun User.toFirebaseModel(): UserNode {
        return UserNode(email, firstName,lastName)
    }

    fun UserNode.toDomainModel(): User {
        return User(email, firstName , lastName)
    }

}