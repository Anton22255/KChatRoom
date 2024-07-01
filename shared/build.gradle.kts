import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
//    kotlin("plugin.serialization") version "1.8.10"
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

repositories {
    maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
}

kotlin {

    js(IR) { browser() }

//    @OptIn(ExperimentalWasmDsl::class)
//    wasmJs {
//        browser {
//            commonWebpackConfig {
//                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
//                    static = (static ?: mutableListOf()).apply {
//                        // Serve sources to debug inside browser
//                        add(project.projectDir.path)
//                    }
//                }
//            }
//        }
//    }
    
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    jvm()
    
    sourceSets {
        commonMain.dependencies {
//            implementation("libs.kotlinx.serialization.json")
//            api("libs.kotlinx.serialization.json")
            implementation(compose.ui)
            implementation(compose.foundation)
            implementation(compose.material)
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)

//            api("libs.kotlinx.serialization.json")
//            api("libs.kotlinx.datetime")

            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            api(libs.kotlinx.datetime)
            implementation(libs.ktor.client.logging)
            // put your Multiplatform dependencies here
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.okhttp)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(libs.ktor.client.okhttp)
            }
        }
//        val wasmJsMain by getting {
//            dependencies {
//                implementation(libs.io.ktor.ktor.client.js2)
////                implementation(libs.io.ktor.ktor.client.serialization)
//            }
//        }
//        val jsWasmMain by creating {
//            dependencies {
//                implementation(project(":shared"))
//                implementation(compose.runtime)
//                implementation(compose.ui)
//                implementation(compose.foundation)
//                implementation(compose.material)
//                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
//                implementation(compose.components.resources)
//            }
//        }

        val jsMain by getting {
//            dependsOn(jsWasmMain)
            dependencies {
                implementation(libs.ktor.client.core)
                implementation(libs.io.ktor.ktor.client.js2)
            }
        }
//        val wasmJsMain by getting {
//            dependsOn(jsWasmMain)
//        }

//        val jsWasmMain by creating {
//            dependencies {
//                implementation(project(":shared"))
//                implementation(compose.runtime)
//                implementation(compose.ui)
//                implementation(compose.foundation)
//                implementation(compose.material)
//                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
//                implementation(compose.components.resources)
//                implementation(libs.ktor.client.core)
//                implementation(libs.io.ktor.ktor.client.js2)
//            }
//        }

//        val wasmJsMain by getting {
//            dependsOn(jsWasmMain)
//        }

    }
}

android {
    namespace = "org.anton22255.kchatroom.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
