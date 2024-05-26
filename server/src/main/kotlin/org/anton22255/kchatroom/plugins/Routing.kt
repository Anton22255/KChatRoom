package org.anton22255.kchatroom.plugins

import Greeting
import org.anton22255.kchatroom.route.routeRoom
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureRouting() {
    routing {
        routeRoom()

        //prev test
        route("/") {
            get {
                call.respondText("Ktor: ${Greeting().greet()}")
            }
        }
    }
}
