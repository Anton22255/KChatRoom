package org.anton22255.kchatroom.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import org.anton22255.kchatroom.Messages
import org.anton22255.kchatroom.ext.readMessage
import org.anton22255.kchatroom.repository.MessageStore
import org.anton22255.kchatroom.service.Services
import ru.anton22255.chat.model.ChatRoom
import ru.anton22255.chat.model.ChatUser
import ru.anton22255.chat.model.request.ChatType
import java.time.Duration
import java.util.*

fun Application.configureSockets() {
    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.AccessControlAllowOrigin)
        allowHeader("Content-Type")

        anyHost() //TODO: don't do this in production
        allowHeaders { true }
    }

    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    routing {
        val chatService = Services.chat
        val sessions = Collections.synchronizedSet<UserSession>(LinkedHashSet())
        val messagesStore = MessageStore()

        webSocket("/chat/{roomId}") {
            val roomId = call.parameters["roomId"]?.toLong()
            val room: ChatRoom = roomId?.let(chatService::getRoom)
                ?: return@webSocket close(CloseReason(CloseReason.Codes.VIOLATED_POLICY, "Invalid room id"))

            val session = UserSession(this, room)
            messagesStore.initRoom(room)
            sessions += session
            try {
                for (frame in incoming) {
                    frame.readMessage()?.let { chat ->
                        when (chat.type) {
                            ChatType.JOIN -> {
                                session.user = chatService.onJoined(room.id, chat.message)

                                messagesStore.getHistoryFlow(room).replayCache.forEach { message ->
                                    session.socket.send(
                                        Messages.message(
                                            message.authorId == session.user?.id,
                                            session.user?.name.orEmpty(),
                                            message.message,
                                        )
                                    )
                                }

                                sessions.filter { it.room.id == room.id }.forEach {
                                    it.socket.send(Messages.join(chat.message))
                                }
                            }

                            ChatType.LEAVE -> {
                                session.user?.id?.let(chatService::onLeave)
                            }

                            ChatType.MESSAGE -> {
                                val user = session.user ?: return@let close(
                                    CloseReason(
                                        CloseReason.Codes.VIOLATED_POLICY,
                                        "Required user name"
                                    )
                                )
                                chatService.onMessage(room.id, user.id, chat)

                                sessions.filter { it.room.id == room.id }.forEach {
                                    val isMine = it.user?.id == session.user?.id
                                    val content = Messages.chat(isMine, session.name, chat)
                                    it.socket.send(content)
                                }
                                messagesStore.saveMessage(
                                    session.room,
                                    session.user?.id!!, chat.message ?: ""
                                )
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                println(e.localizedMessage)
            } finally {
                sessions -= session
            }
        }
    }
}

data class UserSession(val socket: WebSocketSession, var room: ChatRoom, var user: ChatUser? = null) {
    val name get() = user?.name ?: "Unknown"
}