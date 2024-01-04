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
import com.example.myapplication.components.MyTextFieldComponent
import com.example.myapplication.R
import com.example.myapplication.components.ButtonComponent
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.TravelAppNavigate
import com.example.myapplication.components.CheckboxComponent
import com.example.myapplication.components.ClickableLoginTextComponent
import com.example.myapplication.components.DividerTextComponent
import com.example.myapplication.components.HeadingTextComponent
import com.example.myapplication.components.NormalTextComponent
import com.example.myapplication.components.PasswordTextFieldComponent
import com.example.myapplication.data.SignUpViewModel
import com.example.myapplication.data.SignUpUIEvent

@Composable
fun SignUpScreen(signUpViewModel: SignUpViewModel = viewModel()) {
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
            .background(color = Color.White))
    {
        Column(modifier = Modifier.fillMaxSize()) {
            NormalTextComponent(value = stringResource(R.string.hello))
            HeadingTextComponent(value = stringResource(R.string.signup))
            Spacer(modifier = Modifier.height(20.dp))
            MyTextFieldComponent(labelValue = stringResource(R.string.labelValue),
                painterResource(R.drawable.profile),
                onTextSelected ={
                    signUpViewModel.onEvent(SignUpUIEvent.FirstNameChanged(it))
                },
                errorStatus = signUpViewModel.registrationUIState.value.firstNameError
            )
            MyTextFieldComponent(
                labelValue = stringResource(R.string.labelValue2),
                painterResource = painterResource(R.drawable.profile),
                onTextSelected ={
                    signUpViewModel.onEvent(SignUpUIEvent.LastNameChanged(it))
                },
                errorStatus = signUpViewModel.registrationUIState.value.lastNameError
            )
            MyTextFieldComponent(
                labelValue = stringResource(R.string.labelValue3),
                painterResource = painterResource(R.drawable.email),
                onTextSelected ={
                    signUpViewModel.onEvent(SignUpUIEvent.EmailChanged(it))
                },
                errorStatus = signUpViewModel.registrationUIState.value.emailError
            )
            PasswordTextFieldComponent(
                labelValue = stringResource(R.string.labelValue4),
                painterResource = painterResource(R.drawable.password),
                onTextSelected ={
                    signUpViewModel.onEvent(SignUpUIEvent.PasswordChanged(it))
                },
                errorStatus = signUpViewModel.registrationUIState.value.passwordError
            )
            CheckboxComponent(onTextSelected = {
            TravelAppNavigate.navigateTo(Screen.TermsAndConditionsScreen)
        }
            ) {
                signUpViewModel.onEvent(SignUpUIEvent.PrivacyPolicyCheckBoxClicked(it))
            }
            Spacer(modifier = Modifier.height(40.dp))
            ButtonComponent(value = stringResource(id = R.string.register),
                onButtonClicked = {
                    signUpViewModel.onEvent(SignUpUIEvent.RegisterButtonClicked)
                },
                isEnabled = signUpViewModel.allValidationPassed.value)
            DividerTextComponent()
            ClickableLoginTextComponent(tryingToLogin = true,onTextSelected = {
                TravelAppNavigate.navigateTo(Screen.SignInScreen)
            })
        }
        if(signUpViewModel.signUpProgress.value) {
            signUpViewModel.addUser(signUpViewModel.registrationUIState.value.email,signUpViewModel.registrationUIState.value.firstName,signUpViewModel.registrationUIState.value.lastName)
            CircularProgressIndicator()
        }
    }
}