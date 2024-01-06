package com.example.myapplication.data.firebase

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.ImageView
import com.example.myapplication.R
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID


var fileUriData = ""
fun uploadImageToFirebase(fileUri: Uri) {
    val fileName = UUID.randomUUID().toString() +".jpg"
    fileUriData = fileName
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




