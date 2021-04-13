package com.brandontalbot.djcommon.config

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

open class HttpConfig(
    private val configRetriever: ConfigRetriever,
) {
    open val isDebug by lazy {
        configRetriever.getBoolean("DEBUG") ?: false
    }

    open val client: HttpClient by lazy {
        HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = if (isDebug) LogLevel.ALL else LogLevel.INFO
            }
        }
    }
}
