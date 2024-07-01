package org.anton22255.kchatroom.repository

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import ru.anton22255.chat.model.ChatMessage

class MessageStore {
    private val _messages = MutableSharedFlow<ChatMessage>(
        replay = 10,
        extraBufferCapacity = 10,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val messages = _messages.asSharedFlow()

    fun saveMessage(message: ChatMessage) {
        _messages.tryEmit(message) // suspends until all subscribers receive it
    }
}