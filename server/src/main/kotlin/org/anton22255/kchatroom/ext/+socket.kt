package org.anton22255.kchatroom.ext

import io.ktor.websocket.*
import kotlinx.serialization.json.Json
import ru.anton22255.chat.model.request.SendChat


fun Frame.readMessage(): SendChat? = (this as? Frame.Text)?.readText()?.let {
    Json.decodeFromString<SendChat>(it)
}