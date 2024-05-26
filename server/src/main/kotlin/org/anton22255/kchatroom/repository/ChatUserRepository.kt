package org.anton22255.kchatroom.repository

import ru.anton22255.chat.model.ChatRoom
import ru.anton22255.chat.model.ChatUser

interface ChatUserRepository {
    fun getAllByRoomId(roomId: Long): List<ChatUser>
    fun getById(id: Long): ChatUser?
    fun save(room: ChatRoom, userName: String): ChatUser
    fun delete(user: ChatUser)
}