package com.brandontalbot.server

import com.brandontalbot.server.config.ServerContext
import com.brandontalbot.server.feature.installCors
import com.brandontalbot.server.feature.installJson
import com.brandontalbot.server.route.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    val serverConfig = ServerContext.serverConfig

    embeddedServer(
        Netty,
        port = serverConfig.port
    ) {
        installCors()
        installJson()

        routing()
    }.start(wait = true)
}
