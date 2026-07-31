package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val PastelPurple = Color(0xFFCDB4DB)
val DarkPurple = Color(0xFF9F86C0)
val LightPurple = Color(0xFFF3E8FF)
val Background = Color(0xFFF9F5FF)
val CardColor = Color(0xFFFFFFFF)
val ButtonText = Color.White

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Background
                ) {
                    ReactiveScreen()
                }
            }
        }
    }
}

@Composable
fun ReactiveScreen() {

    var count by remember { mutableStateOf(0) }

    var name by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = CardColor
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Reactive Counter",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = PastelPurple
                )

                Text(
                    text = "State & Recomposition",
                    fontSize = 15.sp,
                    color = DarkPurple
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = if (name.isBlank())
                        "Hello, Stranger!"
                    else
                        "Hello, $name!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkPurple
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    label = {
                        Text("Enter your name")
                    },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PastelPurple,
                        focusedLabelColor = PastelPurple,
                        unfocusedBorderColor = LightPurple,
                        cursorColor = PastelPurple
                    )
                )

                Spacer(modifier = Modifier.height(30.dp))

                CounterControls(
                    count = count,
                    onIncrement = {
                        count++
                    },
                    onDecrement = {
                        count--
                    },
                    onReset = {
                        count = 0
                    }
                )
            }
        }
    }
}

@Composable
fun CounterControls(
    count: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    onReset: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Count: $count",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = DarkPurple
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Button(
                onClick = onDecrement,
                colors = ButtonDefaults.buttonColors(
                    containerColor = PastelPurple,
                    contentColor = ButtonText
                )
            ) {
                Text("-")
            }

            Button(
                onClick = onReset,
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkPurple,
                    contentColor = ButtonText
                )
            ) {
                Text("Reset")
            }

            Button(
                onClick = onIncrement,
                colors = ButtonDefaults.buttonColors(
                    containerColor = PastelPurple,
                    contentColor = ButtonText
                )
            ) {
                Text("+")
            }
        }
    }
}