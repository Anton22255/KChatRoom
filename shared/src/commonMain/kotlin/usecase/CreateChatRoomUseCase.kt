package usecase

import common.Http
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import ru.anton22255.chat.model.ChatRoom
import ru.anton22255.chat.model.request.CreateChatRoom
import ru.anton22255.chat.usecase.CreateChatRoomUseCase

class CreateChatRoomUseCaseImpl : CreateChatRoomUseCase {
    override suspend fun execute(input: CreateChatRoom): ChatRoom =
        Http.client.post("room") {
            contentType(ContentType.Application.Json)
            headers.append("Access-Control-Allow-Origin", "*")
            headers.append("Access-Control-Allow-Credentials", "true")
            setBody(input)
        }.body()
}