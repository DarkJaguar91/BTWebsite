package com.brandontalbot.djserver.config

import com.brandontalbot.djcommon.config.ConfigRetriever

open class WebConfig(
    private val configRetriever: ConfigRetriever
) {
    open val webDir by lazy {
        configRetriever.getString("WEB_DIR")
    }
}