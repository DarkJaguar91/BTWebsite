package com.brandontalbot.djserver.routes.web

import com.brandontalbot.djserver.config.AppContext
import io.ktor.http.content.*
import io.ktor.routing.*

fun Route.web(
    webDir: String? = AppContext.webConfig.webDir
) {
    static {
        webDir?.let {
            files(webDir)
        }
        default("index.html")
    }
}
