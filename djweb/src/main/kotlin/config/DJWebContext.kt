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
