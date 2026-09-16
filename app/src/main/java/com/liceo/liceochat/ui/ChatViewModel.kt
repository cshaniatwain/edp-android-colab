package com.liceo.liceochat.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.liceo.liceochat.core.AppResult
import com.liceo.liceochat.data.network.NetworkModule
import com.liceo.liceochat.data.repository.ChatRepositoryImpl
import com.liceo.liceochat.domain.ChatRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ChatViewModel(
    private val repository: ChatRepository
) : ViewModel() {

    var uiState: ChatUiState by mutableStateOf(ChatUiState.Loading)
        private set

    var myName: String by mutableStateOf("")
        private set

    var draft: String by mutableStateOf("")
        private set

    init {
        load()
    }

    fun onNameChange(name: String) {
        myName = name
    }

    fun onDraftChange(newDraft: String) {
        draft = newDraft
    }

    fun send() {
        val currentName = myName
        val currentDraft = draft
        if (currentName.isBlank() || currentDraft.isBlank()) return
        viewModelScope.launch {
            val result = repository.sendMessage(currentName, currentDraft)
            if (result is AppResult.Success) {
                draft = ""
                load()
            }
        }
    }

    fun load() {
        uiState = ChatUiState.Loading

        viewModelScope.launch {
            uiState = when (val r = repository.getMessages()) {
                is AppResult.Success ->
                    if (r.data.isEmpty()) {
                        ChatUiState.Empty
                    } else {
                        ChatUiState.Ready(r.data)
                    }

                AppResult.Failure.NoInternet ->
                    ChatUiState.Error("No internet connection.")

                AppResult.Failure.Timeout ->
                    ChatUiState.Error("The server took too long.")

                is AppResult.Failure ->
                    ChatUiState.Error("Something went wrong.")
            }
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                ChatViewModel(
                    ChatRepositoryImpl(NetworkModule.chatApi)
                )
            }
        }
    }
}