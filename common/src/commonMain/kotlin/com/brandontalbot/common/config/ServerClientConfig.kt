package com.brandontalbot.common.config

import com.brandontalbot.common.client.server.ServerClient
import com.brandontalbot.common.getEnv
import io.ktor.http.URLProtocol

open class ServerClientConfig(
    private val httpConfig: HttpConfig
) {
    open val host by lazy {
        getEnv("SERVER_HOST") ?: error("SERVER_HOST not specified.")
    }

    open val port by lazy {
        getEnv("SERVER_PORT")?.toIntOrNull() ?: error("SERVER_HOST not specified.")
    }

    open val protocol by lazy {
        getEnv("SERVER_PROTOCOL")?.let { URLProtocol.createOrDefault(it) } ?: error("SERVER_HOST not specified.")
    }

    open val client by lazy {
        ServerClient(
            httpConfig.client,
            host,
            port,
            protocol
        )
    }
}
