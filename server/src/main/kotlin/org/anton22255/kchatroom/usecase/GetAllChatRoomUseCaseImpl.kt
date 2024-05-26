package org.anton22255.kchatroom.usecase

import org.anton22255.kchatroom.repository.ChatRoomRepository
import org.anton22255.kchatroom.repository.Repositories
import ru.anton22255.chat.model.ChatRoom
import ru.anton22255.chat.usecase.GetAllChatRoomUseCase

class GetAllChatRoomUseCaseImpl(
    private val repo: ChatRoomRepository = Repositories.room,
) : GetAllChatRoomUseCase {
    override suspend fun execute(input: Unit): List<ChatRoom> =
        repo.getAll()
}
