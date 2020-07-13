package com.brandontalbot.common.config

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

@OptIn(UnstableDefault::class)
open class HttpConfig(
    private val environmentConfig: EnvironmentConfig
) {
    open val client: HttpClient by lazy {
        HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    Json(
                        JsonConfiguration(
                            ignoreUnknownKeys = true
                        )
                    )
                )
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = if (environmentConfig.debug) LogLevel.ALL else LogLevel.INFO
            }
        }
    }
}
