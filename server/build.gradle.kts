plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "org.anton22255.kchatroom"
version = "1.0.0"
application {
    mainClass.set("org.anton22255.kchatroom.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
    api(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.websockets.jvm)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.cors.jvm)

    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
}