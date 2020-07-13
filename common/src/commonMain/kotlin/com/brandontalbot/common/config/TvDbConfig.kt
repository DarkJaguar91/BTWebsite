package com.brandontalbot.common.config

import com.brandontalbot.common.client.tvdb.TvDbClient
import com.brandontalbot.common.getEnv

open class TvDbConfig(
    private val httpConfig: HttpConfig
) {
    open val token by lazy {
        getEnv("TVDB_API_TOKEN") ?: error("TVDB_API_TOKEN is required!")
    }

    open val user by lazy {
        getEnv("TVDB_USER") ?: error("TVDB_USER is required!")
    }

    open val key by lazy {
        getEnv("TVDB_USER_KEY") ?: error("TVDB_USER_KEY is required!")
    }

    open val client: TvDbClient by lazy {
        TvDbClient(
            client = httpConfig.client,
            token = token,
            username = user,
            password = key
        )
    }
}
