package com.example.myapplication.screens
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.components.DCodeIcon
import com.example.myapplication.components.FeatureList
import com.example.myapplication.components.ImageTextList
import com.example.myapplication.components.MyIcons
import com.example.myapplication.components.ProfilePopularList
import com.example.myapplication.data.firebase.emailDb
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.TravelAppNavigate

const val my_description = ""
val profilePopularList = listOf(
    ProfilePopularList("Title", "descrition","",""),
    ProfilePopularList("Title", "description","",""),
    ProfilePopularList("Title", "description","",""),

)

val imageTextList = listOf(
    ImageTextList(DCodeIcon.ImageVectorIcon(MyIcons.Location), "Timisoara, Romania"),
    ImageTextList(DCodeIcon.ImageVectorIcon(MyIcons.Email), emailDb),
)

val moreOptionsList = listOf(
    FeatureList("Edit Profile", DCodeIcon.ImageVectorIcon(MyIcons.Edit), ""),
    FeatureList("Manage Account", DCodeIcon.ImageVectorIcon(MyIcons.AccountBox), ""),
)

@OptIn(
    ExperimentalComposeUiApi::class,
    ExperimentalMaterial3Api::class
)
@Preview
@Composable
fun ProfileScreen() {


    Scaffold(
        modifier = Modifier.semantics {
            testTagsAsResourceId = true
        },
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.txt_profile))
                },
                actions = {
                    IconButton(onClick = { TravelAppNavigate.navigateTo(Screen.SignInScreen)}) {
                        Icon(MyIcons.LogOut, contentDescription = "LogOut")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent,
                ),
            )
        }
    ) { padding ->

        ProfileContent(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(padding)
        ) {
            TopProfileLayout()
            MainProfileContent()
            FooterContent()
        }
    }
}

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        content()
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TopProfileLayout() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(8),
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier.padding(vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    MyIcons.AppIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(60.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = nameUser,
                        style = MaterialTheme.typography.titleLarge
                    )


                }
            }

            Text(
                modifier = Modifier.padding(vertical = 5.dp),
                text = my_description,
                style = MaterialTheme.typography.bodySmall,
            )

            FlowRow(modifier = Modifier.padding(vertical = 5.dp)) {
                imageTextList.forEach {
                    ImageTextContent(
                        modifier = Modifier.padding(vertical = 5.dp),
                        icon = {
                            when (it.icon) {
                                is DCodeIcon.ImageVectorIcon -> Icon(
                                    imageVector = it.icon.imageVector,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(20.dp)
                                )

                                is DCodeIcon.DrawableResourceIcon -> Icon(
                                    painter = painterResource(id = it.icon.id),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(20.dp)
                                )
                            }
                        },
                        text = {
                            Text(
                                text = it.text,
                                style = MaterialTheme.typography.labelLarge,
                            )
                        }
                    )
                }
            }

        }

    }
}

@Composable
fun ImageTextContent(
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon()
        Spacer(modifier = Modifier.width(5.dp))
        text()
        Spacer(modifier = Modifier.width(10.dp))
    }
}

@Composable
fun MainProfileContent() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(8),
    ) {
        Column(modifier = Modifier.padding(5.dp)) {
            Text(
                modifier = Modifier
                    .padding(10.dp),
                text = "My packages",
                style = MaterialTheme.typography.titleMedium,
            )
            PopularContentList()

            Divider(modifier = Modifier.padding(vertical = 15.dp))

        }
    }
}

@Composable
fun PopularContentList() {
    LazyRow {
        items(
            items = profilePopularList,
            itemContent = {
                Surface(
                    modifier = Modifier
                        .width(250.dp)
                        .padding(5.dp),
                    shape = RoundedCornerShape(8),
                    border = BorderStroke(0.1.dp, MaterialTheme.colorScheme.outline)
                ) {
                    Column(modifier = Modifier.padding(5.dp)) {
                        Row(
                            modifier = Modifier.padding(vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {

                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = it.name,
                                style = MaterialTheme.typography.titleSmall,
                            )
                        }

                        Text(
                            modifier = Modifier.padding(vertical = 5.dp),
                            text = it.description,
                            style = MaterialTheme.typography.bodySmall, maxLines = 2,
                        )

                        Row(
                            modifier = Modifier.padding(vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            ImageTextContent(
                                modifier = Modifier.padding(vertical = 5.dp),
                                icon = {
                                    Icon(
                                        imageVector = DCodeIcon.ImageVectorIcon(MyIcons.Star).imageVector,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .size(15.dp)
                                    )
                                },
                                text = {
                                    Text(
                                        text = it.star,
                                        style = MaterialTheme.typography.labelLarge,
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun FooterContent() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(8),
    ) {
        Column(modifier = Modifier.padding(5.dp)) {
            moreOptionsList.forEach {
                MoreOptionsComp(it)
            }
        }
    }
}

@Composable
fun MoreOptionsComp(
    featureList: FeatureList,
) {
    Row(
        modifier = Modifier.padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        when (featureList.listIcon) {
            is DCodeIcon.ImageVectorIcon -> Icon(
                imageVector = featureList.listIcon.imageVector,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(6.dp)
            )

            is DCodeIcon.DrawableResourceIcon -> Icon(
                painter = painterResource(id = featureList.listIcon.id),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(6.dp)
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .weight(1f)
        ) {
            Text(
                text = featureList.name,
                style = MaterialTheme.typography.labelLarge
            )
        }
        Icon(
            imageVector = MyIcons.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier.padding(4.dp)
        )
    }
}


