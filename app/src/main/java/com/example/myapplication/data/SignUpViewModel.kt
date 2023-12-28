package com.example.myapplication.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.rules.Validator
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.TravelAppNavigate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

class SignUpViewModel(private val userRepository: UserRepository) :ViewModel() {
    private val TAG = SignUpViewModel::class.simpleName
    var registrationUIState = mutableStateOf(RegistrationUIState())
    var allValidationPassed = mutableStateOf(false)
    var signUpProgress = mutableStateOf(false)

    fun addUser(email: String,firstName:String,lastName: String) {
        userRepository.addUser(User(email, firstName , lastName ))
    }
    fun onEvent(event : SignUpUIEvent){
        validateDataWithRules()
        when(event){
            is SignUpUIEvent.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    firstName = event.firstName
                )
            }
            is SignUpUIEvent.LastNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    lastName = event.lastName
                )
            }
            is SignUpUIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
            }
            is SignUpUIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
            }
            is SignUpUIEvent.RegisterButtonClicked -> {
                signUp()
            }
            is SignUpUIEvent.PrivacyPolicyCheckBoxClicked->{
                registrationUIState.value = registrationUIState.value.copy(
                    privacyPolicyAccepted = event.status
                )
            }
        }

    }

    private fun signUp() {
        validateDataWithRules()
        createUserInFirebase(
            email = registrationUIState.value.email ,
            password = registrationUIState.value.password,
            firstName = registrationUIState.value.firstName,
            lastName = registrationUIState.value.lastName
        )
    }

    private fun validateDataWithRules() {
        val fNameResult = Validator.validateFirstName(
            fname = registrationUIState.value.firstName
        )
        val lNameResult = Validator.validateLastName(
            lname = registrationUIState.value.lastName
        )
        val emailResult = Validator.validateEmail(
            emailV = registrationUIState.value.email
        )
        val passwordResult = Validator.validatePassword(
            passwordV = registrationUIState.value.password
        )
        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
            statusValue = registrationUIState.value.privacyPolicyAccepted
        )
        registrationUIState.value = registrationUIState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            privacyPolicyError = privacyPolicyResult.status
        )
        allValidationPassed.value = fNameResult.status && lNameResult.status && emailResult.status && passwordResult.status && !privacyPolicyResult.status
    }

    private fun printState(){
        Log.d(TAG,registrationUIState.value.toString())
    }
    private fun createUserInFirebase(email:String,password:String,firstName:String,lastName:String){
        signUpProgress.value = true
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                Log.d(TAG,"Inside_OnCompleteListener")
                Log.d(TAG,"is succesful = ${it.isSuccessful}")
                signUpProgress.value = false
                if(it.isSuccessful){
                    TravelAppNavigate.navigateTo(Screen.ExploreScreen)
                }
            }
            .addOnFailureListener{
                Log.d(TAG,"exception = ${it.message}")
                Log.d(TAG,"exception = ${it.localizedMessage}")
            }
    }
    fun logOut(){
        val firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signOut()
        val authStateListener = AuthStateListener{
            if(it.currentUser == null){

            }
        }
        firebaseAuth.addAuthStateListener(authStateListener)
    }
}