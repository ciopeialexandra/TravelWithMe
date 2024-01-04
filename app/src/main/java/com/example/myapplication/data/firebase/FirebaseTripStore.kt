package com.example.myapplication.data.firebase

import android.util.Log
import com.example.myapplication.data.Trip
import com.example.myapplication.data.TripRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FirebaseTripStore : TripRepository {
    private val database = FirebaseDatabase.getInstance().reference.child("trip")

    override fun getAll(): Flow<List<Trip>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.e("FirebaseTripStore", "getAll:", p0.toException())
            }

            override fun onDataChange(p0: DataSnapshot) {
                val nodeValues = mutableListOf<TripNode>()

                val children = p0.children
                for (child in children) {
                    val tripNode = child.getValue(TripNode::class.java)
                    tripNode?.let{nodeValues.add(tripNode)}
                }
                val items = nodeValues.map { tripNode -> tripNode.toDomainModel() }

                trySend(items)
            }

        }
        database.addListenerForSingleValueEvent(listener)

        awaitClose { database.removeEventListener(listener) }
    }

    override fun addTrip(trip: Trip) {
        database.push().setValue(trip.toFirebaseModel())
    }
    override fun findTrip(email: String):String {
        var location = ""
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.e("FirebaseTripStore", "getAll:", p0.toException())
            }

            override fun onDataChange(p0: DataSnapshot) {

                val children = p0.children
                for (child in children) {
                    if (child.getValue(TripNode::class.java)?.email.equals(email)) {
                        location =
                            child.getValue(TripNode::class.java)?.city + " " +child.getValue(
                                TripNode::class.java
                            )?.country
                    }
                }

            }
        }
        database.addListenerForSingleValueEvent(listener)

        database.removeEventListener(listener)

        return location.ifEmpty {
            "Name not found"
        }

    }


    private fun Trip.toFirebaseModel(): TripNode {
        return TripNode(email,country,description,city,attractions,restaurants,images)
    }

    fun TripNode.toDomainModel(): Trip {
        return Trip(email,country,description,city,attractions,restaurants,images)
    }

}