package com.example.myapplication.screens

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.data.TripViewModel
import com.example.myapplication.data.Trip
import com.google.firebase.Firebase
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
//                Modifier.background(White)
            )

            SetData(tripViewModel)
        }
    }
}
var photo = Uri.EMPTY
@Composable
fun SetData(tripViewModel: TripViewModel) {

    val trips = tripViewModel.tripListState.collectAsState(emptyList())
    val storage = Firebase.storage
    val storageRef = storage.reference
    LazyColumn {
        items(trips.value) { trip ->
            val imageName = trip.images
            val pathReference = storageRef.child("images/$imageName")
            pathReference.downloadUrl.addOnSuccessListener { image ->
                photo = image
                Log.e("ads", photo.toString())
            }
            CardItem(trip = trip, photo = photo)
        }
    }
}


@SuppressLint("ProduceStateDoesNotAssignValue")
@Composable
fun CardItem(trip: Trip, photo: Uri) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(10.dp)
    ){
        Box(modifier = Modifier.fillMaxSize()){
                Image(
                    modifier = Modifier.fillMaxSize(150F),
                    painter = rememberAsyncImagePainter(photo.toString()),
                    contentDescription = "Image",
                    contentScale = ContentScale.FillWidth
                )
            }
            Text(
                text = trip.country,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .background(color = White),
                textAlign = TextAlign.Center,
                color = White
            )
        }
    }




