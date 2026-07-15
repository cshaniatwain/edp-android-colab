package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/* ---------- Nature Color Palette ---------- */

/* ---------- Pastel Lavender Color Palette ---------- */

private val Lavender = Color(0xFFD8C4F2)
private val LightLavender = Color(0xFFEADCF8)
private val LavenderPink = Color(0xFFF5E6FF)
private val SoftPurple = Color(0xFFA78BFA)
private val DarkPurple = Color(0xFF6D5BA8)
private val TextColor = Color(0xFF5B4B7A)
private val WhiteCard = Color.White.copy(alpha = 0.92f)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    BusinessCard()
                }
            }
        }
    }
}

@Composable
fun BusinessCard() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF4FFF4),
                        Color(0xFFE8F5E9),
                        Color(0xFFD7F2D5),
                        Color(0xFFC8E6C9),
                        Color(0xFFB7E4C7)
                    )
                )
            )
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Card(
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.30f)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.profile_pic),
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(170.dp)
                        .clip(CircleShape)
                        .border(
                            5.dp,
                            DarkPurple,
                            CircleShape
                        )
                )

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "✿ Shania Castro ✿",
                    color = DarkPurple,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Bachelor of Science in\nInformation Technology",
                    color = DarkPurple,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 24.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "💻 Aspiring Web Developer",
                    color = Color(0xFF9C6ADE),
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "\"Code with passion,\nDesign with purpose.\"",
                    color = DarkPurple,
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "──────── ❀ Contact Me ❀ ────────",
                    color = SoftPurple,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                ContactCard(
                    icon = Icons.Default.Call,
                    text = "0926-658-5766"
                )

                Spacer(modifier = Modifier.height(12.dp))

                ContactCard(
                    icon = Icons.Default.Share,
                    text = "github.com/Shania Castro"
                )

                Spacer(modifier = Modifier.height(12.dp))

                ContactCard(
                    icon = Icons.Default.Email,
                    text = "cshaniatwain@gmail.com"
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "🌸 Dream • Code • Create 🌸",
                    color = SoftPurple,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Spacer(modifier = Modifier.height(20.dp))

            }

        }

    }

}

@Composable
fun ContactCard(
    icon: ImageVector,
    text: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = WhiteCard,
                shape = RoundedCornerShape(18.dp)
            )
            .border(
                width = 1.dp,
                color = DarkPurple,
                shape = RoundedCornerShape(18.dp)
            )
            .padding(16.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = DarkPurple,
            modifier = Modifier.size(22.dp)
        )

        Spacer(modifier = Modifier.width(14.dp))

        Text(
            text = text,
            color = TextColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewBusinessCard() {

    MaterialTheme {
        BusinessCard()
    }

}