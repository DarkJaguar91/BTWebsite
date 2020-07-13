package com.brandontalbot.common

import kotlin.browser.window


actual fun getEnv(name: String): String? = when (name) {
    "SERVER_HOST" -> window.location.hostname
    "SERVER_PORT" -> getPort()
    "SERVER_PROTOCOL" -> window.location.protocol.removeSuffix(":")
    "DEBUG" -> window.location.hostname.equals("localhost", ignoreCase = true).toString()
    else -> error("Environment var with [$name] not expected.")
}

private fun getPort(): String {
    val port = window.location.port
    val secure = window.location.protocol.startsWith("https")

    return when {
        port.isEmpty() -> if (secure) "443" else "80"
        else -> port
    }
}
