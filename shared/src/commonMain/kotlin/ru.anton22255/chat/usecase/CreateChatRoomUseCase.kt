package ru.anton22255.chat.usecase

import ru.anton22255.chat.model.ChatRoom
import ru.anton22255.chat.model.request.CreateChatRoom

interface CreateChatRoomUseCase : UseCase<CreateChatRoom, ChatRoom>