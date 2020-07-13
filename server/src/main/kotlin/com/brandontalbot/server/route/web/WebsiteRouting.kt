package com.brandontalbot.server.route.web

import com.brandontalbot.server.config.ServerConfig
import com.brandontalbot.server.config.ServerContext
import io.ktor.http.content.*
import io.ktor.routing.*
import org.slf4j.LoggerFactory
import java.io.File

private val logger = LoggerFactory.getLogger("WebsiteRouting")

fun Routing.website(
    serverConfig: ServerConfig = ServerContext.serverConfig
) {
    logger.info("Static resources: ${serverConfig.webDir}")

    static("/") {
        resources()
        files(serverConfig.webDir)
        default(File(serverConfig.webDir, "index.html"))
    }
}
