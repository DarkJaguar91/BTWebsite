package com.brandontalbot.common.config

import com.brandontalbot.common.client.sonarr.SonarrClient
import com.brandontalbot.common.getEnv
import io.ktor.http.URLProtocol

open class SonarrConfig(
    private val httpConfig: HttpConfig
) {
    open val host by lazy {
        getEnv("SONARR_HOST") ?: error("SONARR_HOST not sepcified.")
    }

    open val port by lazy {
        getEnv("SONARR_PORT")?.toIntOrNull() ?: error("SONARR_PORT not sepcified.")
    }

    open val protocol by lazy {
        getEnv("SONARR_PROTOCOL")?.let {
            URLProtocol.createOrDefault(it)
        } ?: error("SONARR_PROTOCOL not sepcified.")
    }

    open val token by lazy {
        getEnv("SONARR_TOKEN") ?: error("SONARR_TOKEN not sepcified.")
    }

    open val client by lazy {
        SonarrClient(
            httpConfig.client,
            host,
            port,
            protocol,
            token
        )
    }
}
