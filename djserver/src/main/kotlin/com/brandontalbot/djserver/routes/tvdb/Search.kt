package com.brandontalbot.djserver.routes.tvdb

import com.brandontalbot.djcommon.client.tvdb.TvDbClient
import com.brandontalbot.djserver.config.AppContext
import io.ktor.response.*
import io.ktor.routing.*

fun Route.searchTvDbRoute(
    tvdb: TvDbClient = AppContext.tvdbConfig.client
) {
    get("/search/{pattern...}") {
        val pattern = context.parameters["pattern"]!!

        context.respond(tvdb.search(pattern))
    }
}
