package com.brandontalbot.djcommon.config

import com.brandontalbot.djcommon.client.tvdb.TvDbClient

open class TvdbConfig(
    private val httpConfig: HttpConfig,
    private val configRetriever: ConfigRetriever
) {
    open val token by lazy {
        configRetriever.getString("TVDB_API_TOKEN") ?: error("TVDB_API_TOKEN is required!")
    }

    open val user by lazy {
        configRetriever.getString("TVDB_USER") ?: error("TVDB_USER is required!")
    }

    open val key by lazy {
        configRetriever.getString("TVDB_USER_KEY") ?: error("TVDB_USER_KEY is required!")
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
