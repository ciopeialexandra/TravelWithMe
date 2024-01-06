package com.example.myapplication.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.data.TripViewModel
import com.example.myapplication.data.Trip
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(tripViewModel: TripViewModel = viewModel()){
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(color = White)
    ){
        Column {
            TopAppBar(
                title = {
                    Text(text = "Trips")
                },
//                Modifier.background(Black),

            )

            SetData(tripViewModel)
        }
    }
}


@Composable
fun SetData(tripViewModel: TripViewModel) {
    val trips = tripViewModel.tripListState.collectAsState(emptyList())
    val storage = Firebase.storage
    val storageRef = storage.reference

    LazyColumn {
        items(trips.value) { trip ->
            CardItem(trip = trip, storageRef = storageRef)
        }
    }
}

@Composable
fun CardItem(trip: Trip, storageRef: StorageReference) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    DisposableEffect(key1 = trip.images) {
        val pathReference = storageRef.child("images/${trip.images}")

        val onSuccessListener = OnSuccessListener<Uri> { image ->
            imageUri = image // Store the image URI
        }

        pathReference.downloadUrl.addOnSuccessListener(onSuccessListener)

        onDispose {}
    }

    val painter = rememberAsyncImagePainter(imageUri?.toString())

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painter,
                modifier = Modifier.fillMaxSize(),
                contentDescription = "Image",
                contentScale = ContentScale.FillWidth
            )

            Text(
                text = trip.country,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray),
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }
    }
}




