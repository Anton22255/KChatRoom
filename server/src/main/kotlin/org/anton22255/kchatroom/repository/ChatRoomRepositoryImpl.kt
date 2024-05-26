package org.anton22255.kchatroom.repository

import ru.anton22255.chat.model.ChatRoom
import java.util.concurrent.atomic.AtomicLong

class ChatRoomRepositoryImpl : ChatRoomRepository {
    private val id = AtomicLong()
    private val rooms = hashMapOf<Long, ChatRoom>()
    override fun getById(id: Long): ChatRoom? = rooms[id]
    override fun getAll(): List<ChatRoom> = rooms.values.toList()
    override fun save(room: ChatRoom) {
        rooms[room.id] = room
    }
    override fun incrementAndGetId(): Long = id.incrementAndGet()
}