package com.example.myapplication.screens

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(tripViewModel: TripViewModel = viewModel()){
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
                text = trip.country,
                //fontSize = MaterialTheme.typography.titleSmall
                modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().background(color = Color.White),
                textAlign = TextAlign.Center,
                color = White
            )
        }
    }
}

