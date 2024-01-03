
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myapplication.data.LoginViewModel
import com.example.myapplication.data.SignUpViewModel
import com.example.myapplication.data.TripViewModel
import com.example.myapplication.data.firebase.FirebaseTripStore
import com.example.myapplication.data.firebase.FirebaseUserStore
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.TravelAppNavigate
import com.example.myapplication.screens.AddTripScreen
import com.example.myapplication.screens.ExploreScreen
import com.example.myapplication.screens.ForgotPasswordScreen
import com.example.myapplication.screens.MainScreen
import com.example.myapplication.screens.ProfileScreen
import com.example.myapplication.screens.SignInScreen
import com.example.myapplication.screens.SignUpScreen
import com.example.myapplication.screens.TermsAndCondtionsScreen

@Composable
fun TravelApp(){
    lateinit var viewModel: SignUpViewModel
    lateinit var viewModel2: LoginViewModel
    lateinit var viewModel3: TripViewModel
    Surface (modifier = Modifier.fillMaxSize(),
        color = Color.White
    ){
        Crossfade(targetState = TravelAppNavigate.currentScreen) { currentState->
            when(currentState.value){
                is Screen.SignUpScreen->{
                    viewModel = SignUpViewModel(FirebaseUserStore())
                    SignUpScreen(viewModel)
                }
                is Screen.TermsAndConditionsScreen->{
                    TermsAndCondtionsScreen()
                }
                is Screen.SignInScreen->{
                    viewModel2 = LoginViewModel(FirebaseUserStore())
                    SignInScreen(viewModel2)
                }
                is Screen.ForgotPasswordScreen->{
                    ForgotPasswordScreen()
                }
                is Screen.MainScreen->{
                    MainScreen()
                }
                is Screen.AddTripScreen->{
                    viewModel3  = TripViewModel(FirebaseTripStore())
                    AddTripScreen(viewModel3)
                }
                is Screen.ExploreScreen->{
                    ExploreScreen()
                }

                is Screen.ProfileScreen ->{
                    ProfileScreen()
                }
            }

        }

    }
}