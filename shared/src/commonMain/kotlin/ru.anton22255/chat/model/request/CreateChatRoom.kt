package ru.anton22255.chat.model.request

import kotlinx.serialization.Serializable

@Serializable
data class CreateChatRoom(
    val name: String,
    val limit: Int
)