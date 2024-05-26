package org.anton22255.kchatroom.usecase

import org.anton22255.kchatroom.ext.now
import org.anton22255.kchatroom.repository.ChatRoomRepository
import org.anton22255.kchatroom.repository.Repositories
import ru.anton22255.chat.model.ChatRoom
import ru.anton22255.chat.model.request.CreateChatRoom
import ru.anton22255.chat.usecase.CreateChatRoomUseCase

class CreateChatRoomUseCaseImpl(
    private val repo: ChatRoomRepository = Repositories.room,
) : CreateChatRoomUseCase {
    override suspend fun execute(input: CreateChatRoom): ChatRoom {
        val room = ChatRoom(repo.incrementAndGetId(), input.name, input.limit, mutableListOf(), now())
        repo.save(room)
        return room
    }
}