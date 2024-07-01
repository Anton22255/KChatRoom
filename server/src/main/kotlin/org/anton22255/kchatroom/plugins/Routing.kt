package org.anton22255.kchatroom.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.anton22255.kchatroom.route.routeRoom


fun Application.configureRouting() {
    routing {
        routeRoom()
    }
}
