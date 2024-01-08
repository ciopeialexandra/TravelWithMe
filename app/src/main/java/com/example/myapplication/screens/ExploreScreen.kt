package com.example.myapplication.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.components.CardItem
import com.example.myapplication.components.TripDetailView
import com.example.myapplication.data.TripViewModel
import com.example.myapplication.data.Trip
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(tripViewModel: TripViewModel = viewModel()) {
    val trips = tripViewModel.tripListState.collectAsState(emptyList())
    val storage = Firebase.storage
    val storageRef = storage.reference

    var selectedCity by remember { mutableStateOf<String?>(null) }
    var selectedTrip by remember { mutableStateOf<Trip?>(null) }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(text = "Popular cities")
            },
            modifier = Modifier.padding(10.dp)
        )

        if (selectedCity != null && selectedTrip == null) {
            Button(
                onClick = { selectedCity = null },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Back to Cities")
            }

            val cityTrips = trips.value.filter { it.city == selectedCity }
            TripListViewExplore(cityTrips = cityTrips, storageRef = storageRef) {
                selectedTrip = it
            }

        } else {
            LazyColumn {
                val citiesWithMoreThanTwoTrips = trips.value.groupBy { it.city }
                    .filter { it.value.size > 1 }
                    .mapNotNull { (_, cityTrips) -> cityTrips.firstOrNull() }

                items(citiesWithMoreThanTwoTrips) { trip ->
                    CardItem(
                        trip = trip,
                        storageRef = storageRef
                    ) {
                        selectedCity = trip.city
                    }
                }
            }
        }

        if (selectedTrip != null) {
            TripDetailView(
                trip = selectedTrip!!,
                storageRef = storageRef
            ) {
                selectedTrip = null
            }
        }
    }
}

@Composable
fun TripListViewExplore(cityTrips: List<Trip>, storageRef: StorageReference, onItemClick: (Trip) -> Unit) {
    LazyColumn {
        items(cityTrips) { trip ->
            TripItemExplore(
                trip = trip,
                storageRef = storageRef,
                onClick = { onItemClick(trip) }
            )
        }
    }
}

@Composable
fun TripItemExplore(trip: Trip, storageRef: StorageReference, onClick: () -> Unit) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    DisposableEffect(key1 = trip.images) {
        val pathReference = storageRef.child("images/${trip.images}")

        val onSuccessListener = OnSuccessListener<Uri> { image ->
            imageUri = image // Store the image URI
        }

        pathReference.downloadUrl.addOnSuccessListener(onSuccessListener)

        onDispose {}
    }
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
            .background(color = Color.Black)
    ){
    }

    val painter = rememberAsyncImagePainter(imageUri?.toString())

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .height(150.dp)
            .padding(2.dp)
            .clickable { onClick.invoke() }
        ) {
            Image(
                painter = painter,
                modifier = Modifier.fillMaxSize(),
                contentDescription = "Image",
                contentScale = ContentScale.FillWidth
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.Transparent)
                    .width(IntrinsicSize.Min)
                    .wrapContentWidth(align = Alignment.Start)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location Icon",
                    tint = Color.Black,
                    modifier = Modifier.padding(end = 4.dp)
                )

                Text(
                    text = trip.city,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    modifier = Modifier.padding(vertical = 4.dp),
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black ,
                )
            }
        }
    }
}





