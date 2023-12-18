package com.example.myapplication.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.components.ButtonComponent
import com.example.myapplication.components.ClickableLoginTextComponent
import com.example.myapplication.components.ClickablePasswordTextComponent
import com.example.myapplication.components.DividerTextComponent
import com.example.myapplication.components.HeadingTextComponent
import com.example.myapplication.components.MyTextFieldComponent
import com.example.myapplication.components.NormalTextComponent
import com.example.myapplication.components.PasswordTextFieldComponent
import com.example.myapplication.data.LoginUIEvent
import com.example.myapplication.data.LoginViewModel
import com.example.myapplication.data.SignUpUIEvent
import com.example.myapplication.data.SignUpViewModel
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.SystemBackButtonHandler
import com.example.myapplication.navigation.TravelAppNavigate

@Composable
fun SignInScreen(loginViewModel: LoginViewModel = viewModel()){
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
                id = R.drawable.email),
                onTextSelected ={
                    loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                },
                errorStatus = loginViewModel.loginUIState.value.emailError
                )
            PasswordTextFieldComponent(labelValue = stringResource(id = R.string.labelValue4), painterResource = painterResource(
                id = R.drawable.password),
                onTextSelected ={
                    loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                },
                errorStatus = loginViewModel.loginUIState.value.passwordError
            )
            ClickablePasswordTextComponent(onTextSelected = {
                TravelAppNavigate.navigateTo(Screen.ForgotPasswordScreen)
            })
            Spacer(modifier = Modifier.height(60.dp))
            ButtonComponent(value = stringResource(id = R.string.login),
                onButtonClicked = {
                                  loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                },
                isEnabled = loginViewModel.allValidationPassed.value
            )
            DividerTextComponent()
            ClickableLoginTextComponent(tryingToLogin = false,onTextSelected = {
                TravelAppNavigate.navigateTo(Screen.SignUpScreen)
            })

        }
        if(loginViewModel.loginProgress.value) {
            CircularProgressIndicator()
        }

    }
    SystemBackButtonHandler {
        TravelAppNavigate.navigateTo(Screen.SignUpScreen)
    }
}
