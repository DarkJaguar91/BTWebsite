package com.brandontalbot.website.config

import com.brandontalbot.common.config.EnvironmentConfig
import com.brandontalbot.common.config.HttpConfig
import com.brandontalbot.common.config.ServerClientConfig

object WebsiteContext {
    val environmentConfig by lazy {
        EnvironmentConfig()
    }

    val httpConfig by lazy {
        HttpConfig(environmentConfig)
    }

    val serverClientConfig by lazy {
        ServerClientConfig(httpConfig)
    }
}
