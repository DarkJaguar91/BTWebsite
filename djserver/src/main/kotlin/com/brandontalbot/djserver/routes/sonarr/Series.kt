package com.brandontalbot.djserver.routes.sonarr

import com.brandontalbot.djcommon.client.sonarr.SonarrClient
import com.brandontalbot.djcommon.client.sonarr.model.SonarrSeries
import com.brandontalbot.djserver.config.AppContext
import com.brandontalbot.djserver.routes.proxyResponse
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.series(
    sonarr: SonarrClient = AppContext.sonarrConfig.client,
) {
    get("/series") {
        context.respond(sonarr.series().map(SonarrSeries::convert))
    }

    get("/MediaCover/{id}/{type}") {
        val id = call.parameters["id"]!!.toLong()
        val type = call.parameters["type"]!!

        val lastWrite = call.request.queryParameters["lastWrite"]

        val response = sonarr.getImage(id, type, lastWrite)

        proxyResponse(response)
    }
}

private fun SonarrSeries.convert(): SonarrSeries = copy(
    images = images.map { image ->
        image.copy(url = "/sonarr${image.url}")
    }
)
