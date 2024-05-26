package org.anton22255.kchatroom.usecase

import org.anton22255.kchatroom.repository.ChatRoomRepository
import org.anton22255.kchatroom.repository.Repositories
import ru.anton22255.chat.model.ChatRoom
import ru.anton22255.chat.usecase.GetChatRoomUseCase

class GetChatRoomUseCaseImpl(
    private val repo: ChatRoomRepository = Repositories.room,
) : GetChatRoomUseCase {
    override suspend fun execute(input: Long): ChatRoom? = repo.getById(input)
}