package com.example.myapplication.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.components.CardItem
import com.example.myapplication.components.TripDetailView
import com.example.myapplication.data.TripViewModel
import com.example.myapplication.data.Trip
import com.google.firebase.Firebase
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