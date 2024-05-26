package org.anton22255.kchatroom.service

import org.anton22255.kchatroom.ext.now
import org.anton22255.kchatroom.repository.ChatRepository
import org.anton22255.kchatroom.repository.ChatRoomRepository
import org.anton22255.kchatroom.repository.ChatUserRepository
import io.ktor.server.plugins.*
import ru.anton22255.chat.model.Chat
import ru.anton22255.chat.model.ChatUser
import ru.anton22255.chat.model.request.SendChat
import java.util.concurrent.atomic.AtomicLong

class ChatService(
    private val chatRepository: ChatRepository,
    private val roomRepository: ChatRoomRepository,
    private val userRepository: ChatUserRepository,
) {
    private val idCounter = AtomicLong()
    fun onJoined(roomId: Long, userName: String): ChatUser {
        val room = roomRepository.getById(roomId) ?: throw BadRequestException("invalid room id")
        return userRepository.save(room, userName).also(room::addUser)
    }

    fun onMessage(roomId: Long, userId: Long, chat: SendChat) {
        val room = getRoom(roomId) ?: throw BadRequestException("invalid room id")
        val user = getUser(userId) ?: throw BadRequestException("invalid user id")

        chatRepository.save(Chat(idCounter.incrementAndGet(), room, user, chat.message, now()))
    }

    fun onLeave(userId: Long) {
        getUser(userId)?.let(userRepository::delete)
    }

    fun getUser(id: Long) = userRepository.getById(id)
    fun getRoom(id: Long) = roomRepository.getById(id)
}