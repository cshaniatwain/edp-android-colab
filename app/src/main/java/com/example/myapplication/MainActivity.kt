package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF7F3FF)
                ) {
                    GroceryListApp()
                }
            }
        }
    }
}

@Composable
fun GroceryListApp() {

    var newItem by remember { mutableStateOf("") }

    val groceries = remember {
        mutableStateListOf<String>()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F3FF))
            .padding(20.dp)
    ) {

        Text(
            text = "🛒 Grocery List",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF7B1FA2)
        )

        Text(
            text = "Organize your shopping easily!",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(20.dp))


        Card(
            shape = RoundedCornerShape(22.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            border = BorderStroke(
                2.dp,
                Color(0xFFD1C4E9)
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                OutlinedTextField(
                    value = newItem,
                    onValueChange = {
                        newItem = it
                    },
                    label = {
                        Text("Enter grocery item")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF9575CD),
                        unfocusedBorderColor = Color(0xFFD1C4E9),
                        cursorColor = Color(0xFF7B1FA2)
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        if (newItem.isNotBlank()) {
                            groceries.add(newItem.trim())
                            newItem = ""
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF66BB6A)
                    ),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text("➕ Add Item")
                }

            }

        }

        Spacer(modifier = Modifier.height(20.dp))


        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFEDE7F6)
            ),
            shape = RoundedCornerShape(18.dp),
            border = BorderStroke(
                2.dp,
                Color(0xFFB39DDB)
            )
        ) {

            Text(
                text = "📦 Total Items: ${groceries.size}",
                modifier = Modifier.padding(16.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF512DA8)
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                groceries.clear()
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFB74D)
            )
        ) {

            Text("🧹 Clear All")

        }

        Spacer(modifier = Modifier.height(20.dp))

        if (groceries.isEmpty()) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                border = BorderStroke(
                    2.dp,
                    Color(0xFFE1BEE7)
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Text(
                    text = "🛍️ Your grocery list is empty.",
                    modifier = Modifier.padding(24.dp),
                    fontSize = 18.sp,
                    color = Color.Gray
                )

            }

        } else {

            LazyColumn {

                items(groceries) { item ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 7.dp),
                        shape = RoundedCornerShape(18.dp),
                        elevation = CardDefaults.cardElevation(6.dp),
                        border = BorderStroke(
                            2.dp,
                            Color(0xFFC5B3FF)
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "🛍️ $item",
                                fontSize = 19.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF4A148C)
                            )

                            Button(
                                onClick = {
                                    groceries.remove(item)
                                },
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFEF5350)
                                )
                            ) {
                                Text("Delete")
                            }

                        }

                    }

                }

            }

        }

    }

}
