package com.example.myapplication.data.firebase

import android.util.Log
import com.example.myapplication.data.StoryRepository
import com.example.myapplication.data.StoryUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


class FirebaseStoryStore : StoryRepository {
    private val database = FirebaseDatabase.getInstance().reference.child("story")

    override fun getAll(): Flow<List<StoryUser>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.e("FirebaseTripStore", "getAll:", p0.toException())
            }

            override fun onDataChange(p0: DataSnapshot) {
                val nodeValues = mutableListOf<StoryNode>()

                val children = p0.children
                for (child in children) {
                    val storyNode = child.getValue(StoryNode::class.java)
                    storyNode?.let { nodeValues.add(storyNode) }
                }
                val items = nodeValues.map { storyNode -> storyNode.toDomainModel() }

                trySend(items)
            }

        }
        database.addListenerForSingleValueEvent(listener)

        awaitClose { database.removeEventListener(listener) }
    }

    override fun addStory(story: StoryUser) {
        database.push().setValue(story.toFirebaseModel())
    }

    override fun findStory(email: String): String {
        var location = ""
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.e("FirebaseTripStore", "getAll:", p0.toException())
            }

            override fun onDataChange(p0: DataSnapshot) {

                val children = p0.children
                for (child in children) {
                    if (child.getValue(StoryNode::class.java)?.email.equals(email)) {
                        location =
                            child.getValue(StoryNode::class.java)?.story + " " + child.getValue(
                                StoryNode::class.java
                            )
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

    private fun StoryUser.toFirebaseModel(): StoryNode {
        return StoryNode(email, story)
    }

    fun StoryNode.toDomainModel(): StoryUser {
        return StoryUser(email,  story)
    }

}