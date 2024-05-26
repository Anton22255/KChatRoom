package org.anton22255.kchatroom.repository

import ru.anton22255.chat.model.ChatRoom
import ru.anton22255.chat.model.ChatUser
import java.util.concurrent.atomic.AtomicLong

class ChatUserRepositoryImpl : ChatUserRepository {
    private val users = hashMapOf<Long, ChatUser>()
    private val idCounters = AtomicLong()
    override fun getById(id: Long): ChatUser? = users[id]
    override fun getAllByRoomId(roomId: Long): List<ChatUser> = users.values.filter { it.roomId == roomId }
    override fun save(room: ChatRoom, userName: String): ChatUser {
        val id = idCounters.incrementAndGet()
        val user = ChatUser(id, room.id, userName)
        users.putIfAbsent(id, user)
        return user
    }

    override fun delete(user: ChatUser) {
        users.remove(user.id)
    }
}