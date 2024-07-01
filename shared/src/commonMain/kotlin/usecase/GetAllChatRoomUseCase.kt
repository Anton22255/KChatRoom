package usecase

import common.Http
import io.ktor.client.call.*
import io.ktor.client.request.*
import ru.anton22255.chat.model.ChatRoom
import ru.anton22255.chat.usecase.GetAllChatRoomUseCase

class GetAllChatRoomUseCaseImpl : GetAllChatRoomUseCase {
    override suspend fun execute(input: Unit): List<ChatRoom> = Http.client.get("room").body()
}
