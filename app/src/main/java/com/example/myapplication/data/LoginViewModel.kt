package com.example.myapplication.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.rules.Validator
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.TravelAppNavigate
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel:ViewModel() {
    private val TAG = LoginViewModel::class.simpleName
    var loginUIState = mutableStateOf(LoginUIState())
    var allValidationPassed = mutableStateOf(false)
    var loginProgress = mutableStateOf(false)
    fun onEvent(event : LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }

            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }

            is LoginUIEvent.LoginButtonClicked -> {
                   signIn()
            }
        }
        validateDataWithRules()
    }
    private fun signIn() {
        val email = loginUIState.value.email
        val password = loginUIState.value.password
        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                Log.d(TAG,"is succesful = ${it.isSuccessful}")
                if(it.isSuccessful){
                    TravelAppNavigate.navigateTo(Screen.MainScreen)
                }
            }
            .addOnFailureListener{
                Log.d(TAG,"exception = ${it.message}")
                Log.d(TAG,"exception = ${it.localizedMessage}")
            }
    }
    private fun validateDataWithRules() {
        val emailResult = Validator.validateEmail(
            emailV = loginUIState.value.email
        )
        val passwordResult = Validator.validatePassword(
            passwordV = loginUIState.value.password
        )
        loginUIState.value = loginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )
        allValidationPassed.value =  emailResult.status && passwordResult.status
    }
}