package com.example.myapplication.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.components.HeadingTextComponent
import com.example.myapplication.components.NormalTextComponent
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.SystemBackButtonHandler
import com.example.myapplication.navigation.TravelAppNavigate

@Composable
fun TermsAndCondtionsScreen(){
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(16.dp)) {
        HeadingTextComponent(value = stringResource(R.string.terms_header))
    }
    SystemBackButtonHandler {
        TravelAppNavigate.navigateTo(Screen.SignUpScreen)
    }
}