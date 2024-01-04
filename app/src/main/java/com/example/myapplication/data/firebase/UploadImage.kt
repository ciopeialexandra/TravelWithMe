package com.example.myapplication.data.firebase

import android.net.Uri
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

fun uploadImageToFirebase(fileUri: Uri) {
    val fileName = UUID.randomUUID().toString() +".jpg"

    FirebaseDatabase.getInstance()
    val refStorage = FirebaseStorage.getInstance().reference.child("images/$fileName")

    refStorage.putFile(fileUri)
        .addOnSuccessListener { taskSnapshot ->
            taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                it.toString()
            }
        }

        .addOnFailureListener { e ->
            print(e.message)
        }
}