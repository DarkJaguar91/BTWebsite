package com.brandontalbot.djserver

import com.brandontalbot.djserver.routes.sonarr.sonarrRoutes
import com.brandontalbot.djserver.routes.tvdb.tvdbRoutes
import com.brandontalbot.djserver.routes.web.webRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json

fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        json(Json {
            isLenient = true
        })
    }
    install(CORS) {
        anyHost()
    }

    tvdbRoutes()
    sonarrRoutes()
    webRoutes()
}
