package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.AccountCircle


private val Maroon = Color(0xFF7A1F3D)
private val DarkMaroon = Color(0xFF4A1025)
private val LightMaroon = Color(0xFFF6E8ED)
private val Cream = Color(0xFFFFFBFC)
private val SoftGray = Color(0xFF6F6F6F)
private val BorderGray = Color(0xFFE3D9DC)
private val White = Color.White


@Composable
fun ProfileForm(
    state: ProfileUiState,
    viewModel: ProfileViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
            .verticalScroll(rememberScrollState())
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Maroon,
                    shape = RoundedCornerShape(
                        bottomStart = 28.dp,
                        bottomEnd = 28.dp
                    )
                )
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 36.dp,
                    bottom = 30.dp
                )
        ) {

            Column {

                Text(
                    text = "My Profile",
                    color = White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = "Create and manage your personal profile",
                    color = White.copy(alpha = 0.80f),
                    fontSize = 14.sp
                )
            }
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            Text(
                text = "Personal Information",
                color = DarkMaroon,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )


            ProfileTextField(
                value = state.name,
                onValueChange = {
                    viewModel.onNameChange(it)
                },
                label = "Full name",
                placeholder = "Enter your full name"
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )


            ProfileTextField(
                value = state.email,
                onValueChange = {
                    viewModel.onEmailChange(it)
                },
                label = "Email",
                placeholder = "Enter your email"
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )


            ProfileTextField(
                value = state.contactNumber,
                onValueChange = {
                    viewModel.onContactChange(it)
                },
                label = "Contact number",
                placeholder = "Enter your contact number"
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )
            
            ProfileTextField(
                value = state.address,
                onValueChange = {
                    viewModel.onAddressChange(it)
                },
                label = "Address",
                placeholder = "Enter your address"
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )


            ProfileTextField(
                value = state.username,
                onValueChange = {
                    viewModel.onUsernameChange(it)
                },
                label = "Username",
                placeholder = "Choose a username"
            )


            Spacer(
                modifier = Modifier.height(26.dp)
            )


            Text(
                text = "Skills",
                color = DarkMaroon,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "Add skills that describe what you can do.",
                color = SoftGray,
                fontSize = 13.sp
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                OutlinedTextField(
                    value = state.newSkill,
                    onValueChange = {
                        viewModel.onNewSkillChange(it)
                    },
                    label = {
                        Text("Skill")
                    },
                    placeholder = {
                        Text("e.g. Kotlin")
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.weight(1f),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Maroon,
                        unfocusedBorderColor = BorderGray,
                        focusedLabelColor = Maroon,
                        cursorColor = Maroon,
                        focusedContainerColor = White,
                        unfocusedContainerColor = White
                    )
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Button(
                    onClick = {
                        viewModel.addSkill()
                    },
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Maroon
                    ),
                    modifier = Modifier.height(56.dp)
                ) {

                    Text(
                        text = "Add",
                        fontWeight = FontWeight.Bold
                    )
                }
            }


            Spacer(
                modifier = Modifier.height(12.dp)
            )

            state.skills.forEach { skill ->

                SkillItem(
                    skill = skill,
                    onRemove = {
                        viewModel.removeSkill(skill)
                    }
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )
            }


            Spacer(
                modifier = Modifier.height(18.dp)
            )


            Button(
                onClick = {
                    viewModel.showPreview()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Maroon
                )
            ) {

                Text(
                    text = "Preview Profile",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )
        }
    }
}


@Composable
private fun ProfileTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(label)
        },
        placeholder = {
            Text(placeholder)
        },
        singleLine = true,
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Maroon,
            unfocusedBorderColor = BorderGray,
            focusedLabelColor = Maroon,
            cursorColor = Maroon,
            focusedContainerColor = White,
            unfocusedContainerColor = White
        )
    )
}

@Composable
private fun SkillItem(
    skill: String,
    onRemove: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(12.dp)
            )
            .background(LightMaroon)
            .padding(
                start = 14.dp,
                end = 6.dp,
                top = 8.dp,
                bottom = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(RoundedCornerShape(50))
                .background(Maroon)
        )

        Spacer(
            modifier = Modifier.width(10.dp)
        )

        Text(
            text = skill,
            color = DarkMaroon,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )

        TextButton(
            onClick = onRemove
        ) {

            Text(
                text = "Remove",
                color = Maroon,
                fontSize = 12.sp
            )
        }
    }
}


@Composable
fun ProfilePreview(
    state: ProfileUiState,
    onBack: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
            .verticalScroll(rememberScrollState())
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Maroon,
                    shape = RoundedCornerShape(
                        bottomStart = 28.dp,
                        bottomEnd = 28.dp
                    )
                )
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 36.dp,
                    bottom = 30.dp
                )
        ) {

            Column {

                Text(
                    text = "Profile Preview",
                    color = White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = "Here's how your profile looks",
                    color = White.copy(alpha = 0.80f),
                    fontSize = 14.sp
                )
            }
        }


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(
                containerColor = White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 3.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(22.dp)
            ) {


                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(LightMaroon)
                        .align(Alignment.CenterHorizontally),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile",
                        tint = Maroon,
                        modifier = Modifier.size(38.dp)
                    )
                }

                Spacer(
                    modifier = Modifier.height(14.dp)
                )




                Text(
                    text = state.name.ifBlank {
                        "Your Name"
                    },
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = DarkMaroon,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )



                Text(
                    text = "@${state.username.ifBlank { "username" }}",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = SoftGray,
                    fontSize = 14.sp
                )

                Spacer(
                    modifier = Modifier.height(22.dp)
                )



                ProfileInfoRow(
                    icon = Icons.Default.Email,
                    label = "Email",
                    value = state.email
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                ProfileInfoRow(
                    icon = Icons.Default.Phone,
                    label = "Contact",
                    value = state.contactNumber
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                ProfileInfoRow(
                    icon = Icons.Default.LocationOn,
                    label = "Address",
                    value = state.address
                )


                Spacer(
                    modifier = Modifier.height(22.dp)
                )



                Text(
                    text = "Skills",
                    color = DarkMaroon,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                if (state.skills.isEmpty()) {

                    Text(
                        text = "No skills added yet.",
                        color = SoftGray,
                        fontSize = 14.sp
                    )

                } else {

                    state.skills.forEach { skill ->

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    LightMaroon,
                                    RoundedCornerShape(10.dp)
                                )
                                .padding(
                                    horizontal = 12.dp,
                                    vertical = 10.dp
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Box(
                                modifier = Modifier
                                    .size(7.dp)
                                    .clip(RoundedCornerShape(50))
                                    .background(Maroon)
                            )

                            Spacer(
                                modifier = Modifier.width(10.dp)
                            )

                            Text(
                                text = skill,
                                color = DarkMaroon,
                                fontSize = 14.sp
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(6.dp)
                        )
                    }
                }
            }
        }



        OutlinedButton(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp
                )
                .height(52.dp),
            shape = RoundedCornerShape(14.dp),
            colors = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(
                contentColor = Maroon
            )
        ) {

            Text(
                text = "Back to Edit",
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )
    }
}



@Composable
private fun ProfileInfoRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(LightMaroon),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Maroon,
                modifier = Modifier.size(21.dp)
            )
        }

        Spacer(
            modifier = Modifier.width(12.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = label,
                color = SoftGray,
                fontSize = 12.sp
            )

            Spacer(
                modifier = Modifier.height(2.dp)
            )

            Text(
                text = value.ifBlank {
                    "Not provided"
                },
                color = DarkMaroon,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}




@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel()
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Cream
    ) {

        if (state.isPreview) {

            ProfilePreview(
                state = state,
                onBack = {
                    viewModel.backToEdit()
                }
            )

        } else {

            ProfileForm(
                state = state,
                viewModel = viewModel
            )
        }
    }
}
