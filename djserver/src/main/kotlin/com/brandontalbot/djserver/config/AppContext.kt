package com.brandontalbot.djserver.config

import com.brandontalbot.djcommon.config.ConfigRetriever
import com.brandontalbot.djcommon.config.HttpConfig
import com.brandontalbot.djcommon.config.SonarrConfig
import com.brandontalbot.djcommon.config.TvdbConfig

object AppContext {
    val tvdbConfig: TvdbConfig by lazy {
        TvdbConfig(httpConfig, configRetriever)
    }

    val sonarrConfig: SonarrConfig by lazy {
        SonarrConfig(httpConfig, configRetriever)
    }

    val httpConfig: HttpConfig by lazy {
        HttpConfig(configRetriever)
    }

    val webConfig: WebConfig by lazy {
        WebConfig(configRetriever)
    }

    val configRetriever: ConfigRetriever by lazy {
        object : ConfigRetriever {
            override fun getString(key: String): String? = System.getenv(key)
        }
    }
}