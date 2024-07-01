package common

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.js.Js

actual fun httpClient(): HttpClientEngine {
    return Js.create {

    }
}