package com.example.myapplication.components

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.BottomBarScreen
import com.example.myapplication.ui.theme.Primary
import com.example.myapplication.ui.theme.labelColor
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.data.LoginUIEvent
import com.example.myapplication.data.LoginViewModel
import com.example.myapplication.data.TripViewModel
import com.example.myapplication.data.firebase.uploadImageToFirebase
import com.example.myapplication.data.firebase.uploadImageToFirebase2
import com.example.myapplication.screens.nameUser
import java.time.LocalDateTime
import java.util.Date

@Composable
fun NormalTextComponent(value:String,direction:String){
    Text(text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = Color.Black,
        textAlign = if (direction == "Center") TextAlign.Center else if (direction == "Left") TextAlign.Left else TextAlign.Right

    )
}

@Composable
fun HeadingTextComponent(value:String){
    Text(text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = Color.Black,
        textAlign = TextAlign.Center

    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextFieldComponent(labelValue: String,onTextSelected: (String) -> Unit,
                         errorStatus:Boolean = false
){
    val textValue = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(ComponentShapes.small),
        label = {Text(text = labelValue)},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            containerColor = labelColor


        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextSelected(it)
        },
        singleLine = true,
        maxLines = 1,
        isError = !errorStatus
    )


}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextFieldComponent(labelValue: String, painterResource: Painter,
                         onTextSelected: (String) -> Unit,
                         errorStatus:Boolean = false
                         ){
    val textValue = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(ComponentShapes.small),
        label = {Text(text = labelValue)},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            containerColor = labelColor


        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        singleLine = true,
        maxLines = 1,
        isError = !errorStatus
    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextFieldComponent(labelValue: String, painterResource: Painter,
                               onTextSelected: (String) -> Unit,
                               errorStatus:Boolean
) {
    val localFocusManager = LocalFocusManager.current
    val password = remember {
        mutableStateOf("")
    }
    val passwordVisible = remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(ComponentShapes.small),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            containerColor = labelColor


        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
        singleLine = true,
        maxLines = 1,
        keyboardActions = KeyboardActions{

                                           localFocusManager.clearFocus()
        },
        value = password.value,
        onValueChange = {
            password.value = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }
            val description = if (passwordVisible.value) {
                "Hide password"
            } else {
                "Show password"
            }
            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)

            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        isError = !errorStatus
    )
}

@Composable
fun CheckboxComponent(onTextSelected: (String) -> Unit, onCheckedChanged: (Boolean) -> Unit){
    Row (modifier = Modifier
        .fillMaxWidth()
        .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ){
        val checkedState = remember {
            mutableStateOf(false)
        }
        Checkbox(checked = checkedState.value, onCheckedChange ={
            checkedState.value = !checkedState.value
            onCheckedChanged.invoke(it)
        } )
        ClickableTextComponent(onTextSelected)
    }

}
@Composable
fun ClickableTextComponent(onTextSelected: (String) -> Unit){
    val initialText = "By continuing this you accept our "
    val privacyPolicyText ="Privacy Policy "
    val andText = "and "
    val termText = "Term of Use"
    val annotatedText = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Primary)) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        append(andText)
        withStyle(style = SpanStyle(color = Primary)) {
            pushStringAnnotation(tag = termText, annotation = termText)
            append(termText)
        }
    }
    ClickableText(text =annotatedText , onClick = {
            offset->annotatedText.getStringAnnotations(offset,offset)
        .firstOrNull()?.also {span->
            Log.d("ClickableTextComponent","{${span.item}}")
            if((span.item==termText)||(span.item==privacyPolicyText)){
                onTextSelected(span.item)
            }
        }
    })
}
@Composable
fun ButtonComponent(value: String,onButtonClicked : ()-> Unit,
                    isEnabled:Boolean = false){
    Button(onClick = {
                     onButtonClicked.invoke()
    },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(50.dp),
        enabled = isEnabled
    ) {
        Box( modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                color = Primary,
                shape = RoundedCornerShape(50.dp)
            ),
            contentAlignment = Alignment.Center
        ) {
            Text(text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

    }
}

@Composable
fun DividerTextComponent(){
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically){
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = Color.Black,
            thickness = 1.dp
        )
        Text(modifier = Modifier.padding(8.dp), text = "or", fontSize = 18.sp,color = Color.Black)
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = Color.Black,
            thickness = 1.dp
        )
    }
}

@Composable
fun ClickableLoginTextComponent(tryingToLogin:Boolean = true,onTextSelected:(String)->Unit){
    val initialText = if(tryingToLogin) "Already have an account?" else "Don't have an account yet?"
    val loginText =  if(tryingToLogin) " Login" else "Register"
    val annotatedText = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Primary)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }
    ClickableText( modifier = Modifier
        .fillMaxWidth()
        .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text =annotatedText , onClick = {
                offset->annotatedText.getStringAnnotations(offset,offset)
            .firstOrNull()?.also {span->
                Log.d("ClickableTextComponent","{${span.item}}")
                if((span.item==loginText)){
                    onTextSelected(span.item)
                }
            }
        })
}
@Composable
fun ClickablePasswordTextComponent(onTextSelected:(String)->Unit){
    val passwordText = "Forgot your password?"
    val clickableText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Primary)) {
            pushStringAnnotation(tag = passwordText, annotation = passwordText)
            append(passwordText)
        }
    }
    ClickableText( modifier = Modifier
        .fillMaxWidth()
        .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Right
        ),
        text =clickableText , onClick = {
                offset->clickableText.getStringAnnotations(offset,offset)
            .firstOrNull()?.also {span->
                Log.d("ClickableTextComponent","{${span.item}}")
                if((span.item==passwordText)){
                    onTextSelected(span.item)
                }
            }
        })
}

@Composable
fun BottomBar(navController : NavHostController){
    val screens = listOf(
        BottomBarScreen.Explore,
        BottomBarScreen.Story,
        BottomBarScreen.Add,
        BottomBarScreen.Search,
        BottomBarScreen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation{
        screens.forEach{ screen ->
            AddItem(screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}


@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
){
    BottomNavigationItem(
        label = {
            androidx.compose.material.Text(text = screen.title)
        },
        icon = {
            androidx.compose.material.Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any{
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route)
        }
    )
}

@Composable
fun LoadImage(
    bitmap: MutableState<Bitmap?>
) {
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        Button(onClick = {
            launcher.launch("image/*")
        }) {
            Text(text = "Upload")
        }
    }
    Log.d("DEBUG", "IMAGE URI $imageUri")
    imageUri?.let { uploadImageToFirebase(it) }
    imageUri?.let {
        val source = ImageDecoder
            .createSource(context.contentResolver, it)
        bitmap.value = ImageDecoder.decodeBitmap(source)
    }
}

@Composable
fun ShowImage() {
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }

    if (bitmap.value != null) {
        Image(
            painter = rememberAsyncImagePainter(bitmap.value),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )
    }

    Row(
        modifier = Modifier
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LoadImage(bitmap = bitmap)
    }
}
var photoPath = Uri.EMPTY
@Composable
fun CameraGalleryChooser(loginViewModel: LoginViewModel) {
    val context = LocalContext.current
    var uriPhoto by remember { mutableStateOf(Uri.EMPTY) }
    val imageUtils = ImageUtils(context)
    var currentPhoto by remember { mutableStateOf<String?>(null) }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val data = it.data?.data
            currentPhoto = if (data == null) {
                // Camera intent
                imageUtils.currentPhotoPath
            } else {
                // Gallery Pick Intent
                uriPhoto = data
                currentPhoto = imageUtils.getPathFromGalleryUri(data)
            }.toString()
        }
    }

    Column {
        Button(
            onClick = { launcher.launch(imageUtils.getIntent())
                      },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp),
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            shape = RoundedCornerShape(50.dp),
            enabled = true
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(48.dp)
                    .background(
                        color = Primary,
                        shape = RoundedCornerShape(50.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Add story",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        if (currentPhoto != null) {
            if(uriPhoto!= Uri.EMPTY) {
                Log.e("Uri", uriPhoto.toString())
                uploadImageToFirebase2(uriPhoto)
            }
           // loginViewModel.onEvent(LoginUIEvent.StoryAdded(currentPhoto.toString()))
            Image(
                painter = rememberAsyncImagePainter(currentPhoto),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()

            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Displaying user name
                Text(

                    text = nameUser,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                // Displaying date
                Text(
                    text = LocalDateTime.now().toString(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}