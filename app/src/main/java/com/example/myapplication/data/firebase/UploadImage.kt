package com.example.myapplication.data.firebase

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
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

fun uploadImageToFirebase2(fileUri: Uri) {
    val fileName = UUID.randomUUID().toString() + ".jpg"
    fileUriData = fileName
    val refStorage = FirebaseStorage.getInstance().reference.child("story/$fileName")

    refStorage.putFile(fileUri)
        .addOnSuccessListener { taskSnapshot ->
            // Image uploaded successfully, now get the download URL
            refStorage.downloadUrl.addOnSuccessListener { uri ->
                // Do something with the download URL (e.g., save it or use it in your app)
                val downloadUrl = uri.toString()
                Log.d("DownloadURL", downloadUrl)

                // You can perform further actions with the download URL here
            }
        }
        .addOnFailureListener { e ->
            // Handle failure
            Log.e("FirebaseStorage", "Upload failed: ${e.message}")
        }
}




