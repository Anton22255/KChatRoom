package usecase

import common.Http
import io.ktor.client.call.*
import io.ktor.client.request.*
import ru.anton22255.chat.model.ChatRoom
import ru.anton22255.chat.usecase.GetChatRoomUseCase

class GetChatRoomUseCaseImpl : GetChatRoomUseCase {
    override suspend fun execute(input: Long): ChatRoom =
        Http.client.get("room/$input").body()
}