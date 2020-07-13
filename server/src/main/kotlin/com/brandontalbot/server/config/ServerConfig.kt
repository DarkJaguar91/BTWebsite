package com.brandontalbot.server.config

open class ServerConfig {
    open val port by lazy {
        System.getenv("PORT")?.toIntOrNull() ?: 80
    }

    open val webDir by lazy {
        System.getenv("WEB_DIR")
    }
}
