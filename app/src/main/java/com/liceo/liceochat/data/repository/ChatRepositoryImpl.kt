package com.liceo.liceochat.data.repository

import com.liceo.liceochat.core.AppResult
import com.liceo.liceochat.data.network.ChatApiService
import com.liceo.liceochat.data.network.dto.NewMessageDto
import com.liceo.liceochat.data.network.dto.toDomain
import com.liceo.liceochat.domain.ChatRepository
import java.io.IOException
import java.net.UnknownHostException
import java.net.SocketTimeoutException
import com.liceo.liceochat.domain.Message

class ChatRepositoryImpl(
    private val api: ChatApiService
) : ChatRepository {

    override suspend fun getMessages(): AppResult<List<com.liceo.liceochat.domain.Message>> {
        return safeCall {
            api.getMessages().toDomain()
        }
    }

    override suspend fun sendMessage(
        sender: String,
        text: String
    ): AppResult<Unit> {
        return safeCall {
            val dto = NewMessageDto(
                sender = sender,
                text = text,
                createdAt = System.currentTimeMillis()
            )

            api.sendMessage(dto)
            Unit
        }
    }

    private suspend fun <T> safeCall(
        block: suspend () -> T
    ): AppResult<T> {
        return try {
            AppResult.Success(block())
        } catch (e: UnknownHostException) {
            AppResult.Failure.NoInternet
        } catch (e: SocketTimeoutException) {
            AppResult.Failure.Timeout
        } catch (e: IOException) {
            AppResult.Failure.NoInternet
        } catch (e: Exception) {
            AppResult.Failure.Unknown(e.message)
        }
    }
}