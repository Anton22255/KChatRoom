package org.anton22255.kchatroom

import SERVER_PORT
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import org.anton22255.kchatroom.plugins.configureRouting
import org.anton22255.kchatroom.plugins.configureSockets

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) { json() }
    configureRouting()
    configureSockets()
}