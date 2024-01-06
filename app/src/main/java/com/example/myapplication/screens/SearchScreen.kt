package com.example.myapplication.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun SearchScreen(tripViewModel: TripViewModel = viewModel()) {
    var searchText by remember { mutableStateOf("") }
    val trips = tripViewModel.tripListState.collectAsState(emptyList())
    val storage = Firebase.storage
    val storageRef = storage.reference

    var selectedTrip by remember { mutableStateOf<Trip?>(null) }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(text = "Trips")
            },
            modifier = Modifier.padding(10.dp)
        )

        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Search by City") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        LazyColumn {
            items(trips.value.filter { it.city.contains(searchText, ignoreCase = true) }) { trip ->
                if (selectedTrip == trip) {
                    TripDetailView(
                        trip = trip,
                        storageRef = storageRef
                    ) {
                        selectedTrip = null
                    }
                } else {
                    CardItem(
                        trip = trip,
                        storageRef = storageRef
                    ) {
                        selectedTrip = trip
                    }
                }
            }

        }
    }
}


@Composable
fun CardItem(trip: Trip, storageRef: StorageReference, onClick: () -> Unit) {
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
        Box(modifier = Modifier
            .fillMaxSize()
            .height(150.dp)
            .padding(10.dp)
            .clickable { onClick.invoke() }
        ) {
            Image(
                painter = painter,
                modifier = Modifier.fillMaxSize(),
                contentDescription = "Image",
                contentScale = ContentScale.FillWidth

            )

            Text(
                text = trip.city,
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

@Composable
fun TripDetailView(trip: Trip, storageRef: StorageReference, onClose: () -> Unit) {

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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 64.dp)
        ){
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                        append("Country: ")
                    }
                    append(trip.country)
                },
                fontSize = 20.sp
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                        append("City: ")
                    }
                    append(trip.city)
                },
                fontSize = 20.sp
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                        append("Description: ")
                    }
                    append(trip.description)
                },
                fontSize = 20.sp
                )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                        append("Attractions: ")
                    }
                    append(trip.attractions)
                },
                fontSize = 20.sp
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                        append("Restaurants: ")
                    }
                    append(trip.restaurants)
                },
                fontSize = 20.sp
            )

//            val painter = rememberAsyncImagePainter(trip.images)
            Image(
                painter = painter,
                contentDescription = "Trip Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onClose,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text("Close")
                }
        }
    }
}




