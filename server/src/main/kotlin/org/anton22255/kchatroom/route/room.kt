package org.anton22255.kchatroom.route

import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.anton22255.kchatroom.usecase.CreateChatRoomUseCaseImpl
import org.anton22255.kchatroom.usecase.GetAllChatRoomUseCaseImpl
import org.anton22255.kchatroom.usecase.GetChatRoomUseCaseImpl
import ru.anton22255.chat.model.request.CreateChatRoom

fun Route.routeRoom() {
    route("room") {
        get { call.respond(GetAllChatRoomUseCaseImpl().execute(Unit)) }

        get("{id}") {
            val roomId = call.parameters["id"]?.toLongOrNull() ?: throw BadRequestException("Invalid parameter")
            val room = GetChatRoomUseCaseImpl().execute(roomId) ?: throw BadRequestException("Invalid room id")
            call.respond(room)
        }

        post {
            val request = call.receive<CreateChatRoom>()
            call.respond(CreateChatRoomUseCaseImpl().execute(request))
        }
        delete { } //TODO
    }
}