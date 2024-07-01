package usecase

import ru.anton22255.chat.usecase.CreateChatRoomUseCase
import ru.anton22255.chat.usecase.GetAllChatRoomUseCase
import ru.anton22255.chat.usecase.GetChatRoomUseCase

object UseCases {
    fun getChatRoom(): GetChatRoomUseCase = GetChatRoomUseCaseImpl()
    fun createChatRoom(): CreateChatRoomUseCase = CreateChatRoomUseCaseImpl()
    fun getAllChatRoom(): GetAllChatRoomUseCase = GetAllChatRoomUseCaseImpl()
}