package com.brandontalbot.djcommon.config

import com.brandontalbot.djcommon.client.server.ServerClient
import io.ktor.http.*

open class ServerConfig(
    private val httpConfig: HttpConfig,
    private val configRetriever: ConfigRetriever,
) {
    open val host by lazy {
        configRetriever.getString("SERVER_HOST")
    }

    open val port by lazy {
        configRetriever.getString("SERVER_PORT")?.toIntOrNull()
    }

    open val protocol by lazy {
        configRetriever.getString("SERVER_PROTOCOL")
    }

    open val client: ServerClient by lazy {
        ServerClient(
            client = httpConfig.client,
            host = host,
            port = port,
            protocol = protocol?.let { URLProtocol.createOrDefault(it) },
        )
    }
}