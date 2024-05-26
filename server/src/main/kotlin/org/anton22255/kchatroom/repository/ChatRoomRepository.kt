package org.anton22255.kchatroom.repository

import ru.anton22255.chat.model.ChatRoom

interface ChatRoomRepository {
    fun getById(id: Long): ChatRoom?
    fun getAll(): List<ChatRoom>
    fun save(room: ChatRoom)
    fun incrementAndGetId(): Long
}