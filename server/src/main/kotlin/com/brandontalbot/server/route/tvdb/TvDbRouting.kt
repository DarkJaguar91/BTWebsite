package com.brandontalbot.server.route.tvdb

import com.brandontalbot.common.client.tvdb.TvDbClient
import com.brandontalbot.server.config.ServerContext
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get

fun Routing.tvdb(
    client: TvDbClient = ServerContext.tvDbConfig.client
) {
    get("series/search/{name}") {
        call.respond(
            client.search(call.parameters["name"]!!).data
        )
    }
}
