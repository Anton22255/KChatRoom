package org.anton22255.kchatroom

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.anton22255.chat.model.ChatMessage
import ru.anton22255.chat.model.request.SendChat

object Messages {
    fun join(userName: String) = Json.encodeToString(
        ChatMessage(false, "$userName has joined")
    )

    fun chat(isMine: Boolean, userName: String, chat: SendChat) =
        Json.encodeToString(
            ChatMessage(isMine, "$userName: ${chat.message}")
        )

    fun message(isMine: Boolean, userName: String, message: String) =
        Json.encodeToString(
            ChatMessage(isMine, "$userName: $message")
        )
}