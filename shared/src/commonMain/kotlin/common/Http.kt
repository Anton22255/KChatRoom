package common

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json

object Http {
    val wsClient by lazy {
        HttpClient(httpClient()) {
            WebSockets {

            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
                level = LogLevel.ALL
            }

            defaultRequest {
                url(HOST_WS)
            }
        }
    }

    val client by lazy {
        HttpClient(httpClient()) {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
                level = LogLevel.ALL
            }

            headers {
                append("Access-Control-Allow-Origin", "*")
            }

            install(ContentNegotiation) {
                json()
//                headersOf("Access-Control-Allow-Origin", "*")
            }


            defaultRequest {
                url(HOST)
            }
        }
    }
}