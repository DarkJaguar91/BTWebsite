package com.brandontalbot.djcommon.config

import com.brandontalbot.djcommon.client.sonarr.SonarrClient
import io.ktor.http.*

open class SonarrConfig(
    private val httpConfig: HttpConfig,
    private val configRetriever: ConfigRetriever,
) {
    open val token by lazy {
        configRetriever.getString("SONARR_TOKEN") ?: error("SONARR_TOKEN is required!")
    }

    open val host by lazy {
        configRetriever.getString("SONARR_HOST") ?: error("SONARR_HOST is required!")
    }

    open val port by lazy {
        configRetriever.getString("SONARR_PORT")?.toIntOrNull() ?: error("SONARR_PORT is required!")
    }

    open val protocol by lazy {
        configRetriever.getString("SONARR_PROTOCOL") ?: error("SONARR_PROTOCOL is required!")
    }

    open val client: SonarrClient by lazy {
        SonarrClient(
            client = httpConfig.client,
            token = token,
            host = host,
            port = port,
            protocol = URLProtocol.createOrDefault(protocol),
        )
    }
}
