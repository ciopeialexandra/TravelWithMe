package com.example.myapplication.data.firebase

import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.ImageView
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

var fileName = ""
fun uploadImageToFirebase(fileUri: Uri) {
    fileName = UUID.randomUUID().toString() +".jpg"
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

fun findImage(imageName: String): ImageView {
    val uriImage = Uri.EMPTY
    val refStorage = FirebaseStorage.getInstance().reference.child("images/$fileName")

    lateinit var image: ImageView

    val imageRef = FirebaseStorage.getInstance().getReferenceFromUrl(imageName)

        imageRef.getBytes(10 * 1024 * 1024).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
            image.setImageBitmap(bitmap);
        }.addOnFailureListener {
            // Handle any errors
        }
    return image

}



