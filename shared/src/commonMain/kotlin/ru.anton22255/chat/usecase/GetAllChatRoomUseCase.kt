package ru.anton22255.chat.usecase

import ru.anton22255.chat.model.ChatRoom

interface GetAllChatRoomUseCase : VoidUseCase<List<ChatRoom>>
