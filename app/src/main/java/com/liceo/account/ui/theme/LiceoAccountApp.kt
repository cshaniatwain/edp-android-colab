package com.liceo.account.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LiceoAccountApp(
    vm: AuthViewModel = viewModel()
) {

    var screen by remember {
        mutableStateOf("login")
    }

    val state = vm.uiState

    when {

        state is AuthUiState.LoggedIn -> {

            ProfileScreen(
                user = state.user,
                onLogout = {
                    vm.logout()
                    screen = "login"
                }
            )
        }

        screen == "register" -> {

            RegisterScreen(
                state = state,
                onCreate = vm::register,
                onGoToLogin = {
                    vm.clearMessage()
                    screen = "login"
                }
            )
        }

        else -> {

            LoginScreen(
                state = state,
                onLogin = vm::login,
                onGoToRegister = {
                    vm.clearMessage()
                    screen = "register"
                }
            )
        }
    }
}