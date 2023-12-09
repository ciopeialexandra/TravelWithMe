package com.example.myapplication.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.components.ButtonComponent
import com.example.myapplication.components.ClickableLoginTextComponent
import com.example.myapplication.components.ClickablePasswordTextComponent
import com.example.myapplication.components.DividerTextComponent
import com.example.myapplication.components.HeadingTextComponent
import com.example.myapplication.components.MyTextFieldComponent
import com.example.myapplication.components.NormalTextComponent
import com.example.myapplication.components.PasswordTextFieldComponent
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.TravelAppNavigate

@Composable
fun SignInScreen(){
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
            .background(color = Color.White))
    {
        Column ( modifier = Modifier
            .fillMaxSize()){
            NormalTextComponent(value = stringResource(id = R.string.login))
            HeadingTextComponent(value = stringResource(id = R.string.signin))
            Spacer(modifier = Modifier.height(80.dp))
            MyTextFieldComponent(labelValue = stringResource(id = R.string.labelValue3), painterResource = painterResource(
                id = R.drawable.email))
            PasswordTextFieldComponent(labelValue = stringResource(id = R.string.labelValue4), painterResource = painterResource(
                id = R.drawable.password))
            ClickablePasswordTextComponent(onTextSelected = {
                TravelAppNavigate.navigateTo(Screen.ForgotPasswordScreen)
            })
            Spacer(modifier = Modifier.height(60.dp))
            ButtonComponent(value = stringResource(id = R.string.login))
            DividerTextComponent()
            ClickableLoginTextComponent(onTextSelected = {
                TravelAppNavigate.navigateTo(Screen.SignUpScreen)
            })

        }

    }
}