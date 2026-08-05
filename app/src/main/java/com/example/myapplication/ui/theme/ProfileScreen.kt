package com.example.labactivity5 // Adjust package name if yours is different

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ReactiveScreen()
                }
            }
        }
    }
}

@Composable
fun ReactiveScreen() {
    // State declaration:
    // 'count' uses remember (or rememberSaveable if you want count to also survive rotation)
    var count by remember { mutableStateOf(0) }

    // 'name' uses rememberSaveable so state survives screen rotation (Part C)
    var name by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // --- Part B: Greeting & Text Field ---
        Text(
            text = if (name.isBlank()) "Hello, stranger!" else "Hello, $name!",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it }, // Write triggers recomposition
            label = { Text("Enter your name") }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // --- Part D: Hoisted Counter Component ---
        CounterControls(
            count = count,
            onIncrement = { count++ },
            onDecrement = { count-- },
            onReset = { count = 0 }
        )
    }
}

// --- Part D (Bonus): Stateless Counter Component ---
@Composable
fun CounterControls(
    count: Int,                // Data flows DOWN
    onIncrement: () -> Unit,   // Events flow UP
    onDecrement: () -> Unit,
    onReset: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Count: $count", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = onDecrement) {
                Text("–")
            }
            Button(onClick = onReset) {
                Text("Reset")
            }
            Button(onClick = onIncrement) {
                Text("+")
            }
        }
    }
}