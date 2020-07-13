package com.brandontalbot.server.route.sonarr

import com.brandontalbot.common.client.sonarr.SonarrClient
import com.brandontalbot.common.client.sonarr.model.SonarrSeries
import com.brandontalbot.common.retryIo
import com.brandontalbot.server.config.ServerContext
import com.brandontalbot.server.route.proxyResponse
import io.ktor.application.*
import io.ktor.client.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import java.io.IOException
import javax.net.ssl.SSLException
import javax.net.ssl.SSLPeerUnverifiedException
import kotlin.reflect.KClass

private val commonSonarrExceptions = listOf<KClass<*>>(
    IOException::class,
    SSLException::class,
    SSLPeerUnverifiedException::class
)

fun Routing.sonarr(
    client: SonarrClient = ServerContext.sonarrConfig.client
) {
    get("series") {
        call.respond(
            retryIo(
                exceptions = commonSonarrExceptions
            ) {
                client.series().map(SonarrSeries::convert)
            }
        )
    }

    get("series/{id}") {
        val id = call.parameters["id"]?.toLongOrNull()

        checkNotNull(id) { "The provided ID needs to be a number." }

        try {
            val series = retryIo(exceptions = commonSonarrExceptions) {
                client.series(id).convert()
            }
            call.respond(series)
        } catch (e: ClientRequestException) {
            if (e.response.status == HttpStatusCode.NotFound) {
                call.respond(HttpStatusCode.NotFound, "Series with id [$id] not found.")
            } else {
                call.respond(HttpStatusCode.InternalServerError, e.message ?: "Unknown error.")
            }
        }
    }

    get("sonarr/MediaCover/{id}/{type}") {
        val id = call.parameters["id"]!!.toLong()
        val type = call.parameters["type"]!!

        val lastWrite = call.request.queryParameters["lastWrite"]

        val response = retryIo(
            exceptions = commonSonarrExceptions
        ) { client.getImage(id, type, lastWrite) }

        proxyResponse(response)
    }
}

private fun SonarrSeries.convert(): SonarrSeries = copy(
    images = images.map { image ->
        image.copy(url = "/sonarr${image.url}")
    }
)