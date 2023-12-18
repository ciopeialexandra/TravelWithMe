
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.TravelAppNavigate
import com.example.myapplication.screens.AddTripScreen
import com.example.myapplication.screens.ExploreScreen
import com.example.myapplication.screens.ForgotPasswordScreen
import com.example.myapplication.screens.MainScreen
import com.example.myapplication.screens.SignInScreen
import com.example.myapplication.screens.SignUpScreen
import com.example.myapplication.screens.TermsAndCondtionsScreen

@Composable
fun TravelApp(){
    Surface (modifier = Modifier.fillMaxSize(),
        color = Color.White
    ){
        Crossfade(targetState = TravelAppNavigate.currentScreen) { currentState->
            when(currentState.value){
                is Screen.SignUpScreen->{
                    SignUpScreen()
                }
                is Screen.TermsAndConditionsScreen->{
                    TermsAndCondtionsScreen()
                }
                is Screen.SignInScreen->{
                    SignInScreen()
                }
                is Screen.ForgotPasswordScreen->{
                    ForgotPasswordScreen()
                }
                is Screen.MainScreen->{
                    MainScreen()
                }
                is Screen.AddTripScreen->{
                    AddTripScreen()
                }
                is Screen.ExploreScreen->{
                    ExploreScreen()
                }
            }

        }

    }
}