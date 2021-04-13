package config

import com.brandontalbot.djcommon.config.ConfigRetriever
import com.brandontalbot.djcommon.config.HttpConfig
import com.brandontalbot.djcommon.config.ServerConfig
import kotlinx.browser.window

object DJWebContext {
    val configRetriever by lazy {
        object : ConfigRetriever {
            override fun getString(key: String): String? {
                return when (key) {
                    "DEBUG" -> "true"
                    "SERVER_PORT" -> window.location.port
                    "SERVER_HOST" -> window.location.hostname
                    "SERVER_PROTOCOL" -> window.location.protocol.removeSuffix(":")
                    else -> null
                }.also {
                    console.log("[$key] -> $it")
                }
            }
        }
    }

    val httpConfig by lazy {
        HttpConfig(configRetriever)
    }

    val serverConfig by lazy {
        ServerConfig(
            httpConfig,
            configRetriever,
        )
    }
}
