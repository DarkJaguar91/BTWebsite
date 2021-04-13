package com.brandontalbot.djserver.routes.sonarr

import io.ktor.application.*
import io.ktor.routing.*

fun Application.sonarrRoutes() {
    routing {
        route("sonarr") {
            series()
        }
    }
}