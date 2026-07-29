package com.example.myapplication

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Class
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.ProfileTheme

val Maroon = Color(0xFF800000)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {

    Scaffold(

        containerColor = MaterialTheme.colorScheme.background,

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "My Profile",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },

                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },

                actions = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Options",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },

        floatingActionButton = {

            FloatingActionButton(
                onClick = {},
                containerColor = Maroon,
                contentColor = Color.White
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }

    ) { padding ->


        Column(

            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                )
                .padding(padding)
                .padding(16.dp),

            horizontalAlignment = Alignment.CenterHorizontally,

            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Maroon)
                    .border(
                        3.dp,
                        Maroon,
                        CircleShape
                    ),

                contentAlignment = Alignment.Center

            ) {

                Text(
                    text = "STC",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

            }


            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Shania Twain Castro",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )


                Text(
                    text = "WEB DEVELOPER",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

            }


            Card(

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(18.dp),

                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),

                border = BorderStroke(
                    1.dp,
                    MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.15f
                    )
                )

            ) {


                Column(

                    modifier = Modifier.padding(16.dp),

                    verticalArrangement = Arrangement.spacedBy(14.dp)

                ) {


                    ContactRow(
                        Icons.Default.Email,
                        "stcastro44184@liceo.edu.ph"
                    )


                    ContactRow(
                        Icons.Default.LocationOn,
                        "Misamis Oriental, Philippines"
                    )


                    ContactRow(
                        Icons.Default.Class,
                        "BSIT 3-2"
                    )


                    ContactRow(
                        Icons.Default.Phone,
                        "09266585766"
                    )


                    ContactRow(
                        Icons.Default.School,
                        "Liceo De Cagayan University"
                    )

                }

            }

        }

    }

}


@Composable
fun ContactRow(

    icon: androidx.compose.ui.graphics.vector.ImageVector,

    detail: String

) {

    Row(

        verticalAlignment = Alignment.CenterVertically

    ) {
        Icon(

            imageVector = icon,

            contentDescription = null,

            tint = Maroon,

            modifier = Modifier.size(24.dp)

        )
        Spacer(

            modifier = Modifier.width(10.dp)

        )
        Text(

            text = detail,

            color = MaterialTheme.colorScheme.onSurface,

            style = MaterialTheme.typography.bodyMedium

        )

    }

}

@Preview(
    name = "Light Mode",
    showBackground = true
)

@Composable
fun ProfileScreenLightPreview() {

    ProfileTheme(
        darkTheme = false
    ) {

        ProfileScreen()

    }

}

@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)

@Composable
fun ProfileScreenDarkPreview() {

    ProfileTheme(
        darkTheme = true
    ) {

        ProfileScreen()

    }

}