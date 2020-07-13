package com.brandontalbot.server.config

import com.brandontalbot.common.config.EnvironmentConfig
import com.brandontalbot.common.config.HttpConfig
import com.brandontalbot.common.config.SonarrConfig
import com.brandontalbot.common.config.TvDbConfig

object ServerContext {
    val environmentConfig by lazy {
        EnvironmentConfig()
    }

    val serverConfig by lazy {
        ServerConfig()
    }

    val httpConfig by lazy {
        HttpConfig(environmentConfig)
    }

    val tvDbConfig by lazy {
        TvDbConfig(httpConfig)
    }

    val sonarrConfig by lazy {
        SonarrConfig(httpConfig)
    }
}
