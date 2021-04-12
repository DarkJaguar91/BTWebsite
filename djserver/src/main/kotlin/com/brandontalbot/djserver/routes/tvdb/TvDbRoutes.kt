package com.brandontalbot.djserver.routes.tvdb

import io.ktor.application.*
import io.ktor.routing.*

fun Application.tvdbRoutes() {
    routing {
        route("/tvdb") {
            searchTvDbRoute()
        }
    }
}
