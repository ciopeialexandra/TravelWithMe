package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.TripViewModel
import coil.compose.rememberImagePainter
import com.example.myapplication.data.Trip
import com.example.myapplication.data.TripUIEvent
import com.example.myapplication.data.TripUIState
import com.example.myapplication.data.firebase.uploadImageToFirebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen1(tripViewModel: TripViewModel = viewModel()){
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(color = Color.White)
    ){
        Column(){
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


@Composable
fun SetData(tripViewModel: TripViewModel) {
    val trips = tripViewModel.tripListState.collectAsState(emptyList()).value
    ShowData(trips)
}

@Composable
fun ShowData(trips: List<Trip>) {
    LazyColumn{
        items(trips){ each ->
            CardItem(each)
        }
    }
}

@Composable
fun CardItem(trip: Trip) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp)
    ){
        Box(modifier = Modifier.fillMaxSize()){
            Image(
                painter = rememberImagePainter(trip.images),
                modifier = Modifier.fillMaxSize(),
                contentDescription = "Image",
                contentScale = ContentScale.FillWidth
            )

            Text(
                text = trip.country!!,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(color = White),
                textAlign = TextAlign.Center,
                color = White
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TripsList(trips: MutableList<Trip>, onEvent: (TripUIEvent) -> Unit) {
    LazyColumn {
        items(trips) { trip ->
            ListItem(
                modifier = Modifier.clickable { onEvent(TripUIEvent.TripClicked(trip)) },
                text = { Text(trip.city) },
                icon = { trip.images.firstOrNull()?.let {
                    Image(
                        painter = rememberImagePainter(trip.images),
                        contentDescription = "Trip Image") } }
            )
        }
    }
}

@Composable
fun TripDetails(tripUIState: TripUIState, onEvent: (TripUIEvent) -> Unit) {
    val trip = tripUIState.selectedTrip ?: return
    Column {
        // Display trip details
        Text(trip.city)
        Image(
            painter = rememberImagePainter(trip.images),
            contentDescription = "Trip Image")

        // Back button
        Button(onClick = { onEvent(TripUIEvent.BackButtonClicked) }) {
            Text("Back")
        }
    }
}

@Composable
fun SearchScreen(tripViewModel: TripViewModel = viewModel()) {
    val tripUIState = tripViewModel.tripUIState.value
    val tripListState = tripViewModel.tripListState.collectAsState(emptyList()).value.toMutableList()

    val onEvent: (TripUIEvent) -> Unit = { event ->
        tripViewModel.onEvent(event)
    }

    if (tripUIState.selectedTrip == null) {
        TripsList(tripListState, onEvent)
    } else {
        TripDetails(tripUIState, onEvent)
    }
}



