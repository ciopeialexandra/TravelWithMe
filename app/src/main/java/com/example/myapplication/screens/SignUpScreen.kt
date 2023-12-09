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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.components.MyTextFieldComponent
import com.example.myapplication.R
import com.example.myapplication.components.ButtonComponent
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.TravelAppNavigate
import com.example.myapplication.components.CheckboxComponent
import com.example.myapplication.components.ClickableLoginTextComponent
import com.example.myapplication.components.ClickableTextComponent
import com.example.myapplication.components.DividerTextComponent
import com.example.myapplication.components.HeadingTextComponent
import com.example.myapplication.components.MyTextFieldComponent
import com.example.myapplication.components.NormalTextComponent
import com.example.myapplication.components.PasswordTextFieldComponent

@Composable
fun SignUpScreen() {
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
                painterResource(R.drawable.profile))
            MyTextFieldComponent(
                labelValue = stringResource(R.string.labelValue2),
                painterResource = painterResource(R.drawable.profile)
            )
            MyTextFieldComponent(
                labelValue = stringResource(R.string.labelValue3),
                painterResource = painterResource(R.drawable.email)
            )
            PasswordTextFieldComponent(
                labelValue = stringResource(R.string.labelValue4),
                painterResource = painterResource(R.drawable.password)
            )
            CheckboxComponent(value = stringResource(id = R.string.terms), onTextSelected = {
                TravelAppNavigate.navigateTo(Screen.TermsAndConditionsScreen)
            })
            Spacer(modifier = Modifier.height(40.dp))
            ButtonComponent(value = stringResource(id = R.string.register))
            DividerTextComponent()
            ClickableLoginTextComponent(onTextSelected = {
                TravelAppNavigate.navigateTo(Screen.SignInScreen)
            })
        }
    }
}
@Preview
@Composable
fun DefaultPreviewOfSignUpScreen(){
    SignUpScreen()
}