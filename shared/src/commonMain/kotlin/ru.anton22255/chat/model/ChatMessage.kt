package ru.anton22255.chat.model

import kotlinx.serialization.Serializable

@Serializable
class ChatMessage(
    val isMine: Boolean,
    val message: String
)