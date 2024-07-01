package org.anton22255.kchatroom.repository

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import ru.anton22255.chat.model.ChatRoom
import java.util.*

class MessageStore {

    private val store = Collections.synchronizedMap(
        HashMap<Long, MutableSharedFlow<MessageVO>>()
    )

    fun initRoom(chatRoom: ChatRoom) {
        store[chatRoom.id] = MutableSharedFlow(
            replay = 10,
            extraBufferCapacity = 10,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    }

    fun getHistoryFlow(chatRoom: ChatRoom): SharedFlow<MessageVO> = store[chatRoom.id]
        ?.asSharedFlow()
        ?: throw IllegalArgumentException("The room with ${chatRoom.id} wasn't init. Call @initRoom")


    fun saveMessage(chatRoom: ChatRoom, authorId: Long, message: String) {
        store[chatRoom.id]?.tryEmit(MessageVO(authorId, message))
    }
}

data class MessageVO(
    val authorId: Long,
    val message: String,
)