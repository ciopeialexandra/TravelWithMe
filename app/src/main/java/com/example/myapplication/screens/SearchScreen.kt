package com.example.myapplication.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
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
import com.example.myapplication.data.firebase.findImage

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


@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun SetData(tripViewModel: TripViewModel = viewModel()) {
    val trips = tripViewModel.tripListState.collectAsState(initial = emptyList())
    ShowData(trips)
}

@Composable
fun ShowData(trips: List<Trip>) {
    LazyColumn{
      items(trips){each ->
          CardItem(each)
      }
    }
}

@SuppressLint("ProduceStateDoesNotAssignValue")
@Composable
fun CardItem(trip: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp)
    ){
        Box(modifier = Modifier.fillMaxSize()){
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = rememberImagePainter(findImage(trip.images)),
                    contentDescription = "Image",
                    contentScale = ContentScale.FillWidth
                )
            }
            Text(
                text = trip.country!!,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .background(color = White),
                textAlign = TextAlign.Center,
                color = White
            )
        }
    }




