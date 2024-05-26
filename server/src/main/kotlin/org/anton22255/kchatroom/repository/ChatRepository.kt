package org.anton22255.kchatroom.repository

import ru.anton22255.chat.model.Chat

interface ChatRepository {
    fun getAllByRoomId(roomId: Long): List<Chat>
    fun save(chat: Chat)
}