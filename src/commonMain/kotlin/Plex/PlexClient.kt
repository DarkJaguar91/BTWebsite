package Plex

import Plex.exception.PinExpiredException
import Plex.exception.UserTokenInvalidException
import Plex.model.PlexPin
import Plex.model.PlexPinStatus
import Plex.model.PlexUser
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class PlexClient(
    private val plexProduct: String,
    private val plexIdentity: String,
    private val httpClient: HttpClient
) {
    suspend fun generatePin(): PlexPin = httpClient.post {
        plexPath("pins") {
            parameters.append("strong", "true")
        }
    }.body()

    suspend fun checkPin(pin: PlexPin): PlexPinStatus = httpClient.get {
        plexPath("pins", pin.id.toString()) {
            parameters.append("code", pin.code)
        }
    }.also {
        when (it.status) {
            HttpStatusCode.NotFound -> throw PinExpiredException()
        }
    }.body()

    suspend fun getUser(userToken: String): PlexUser = httpClient.get {
        plexPath("user") {
            parameters.append(PLEX_TOKEN, userToken)
        }
    }.also {
        when (it.status) {
            HttpStatusCode.Unauthorized -> throw UserTokenInvalidException()
        }
    }.body()

    private fun HttpRequestBuilder.plexPath(vararg paths: String, extraUrlConfig: URLBuilder.() -> Unit = {}) {
        url {
            protocol = PROTOCOL
            host = HOST
            path("api", "v2", *paths)
            parameters.append(PLEX_PRODUCT, plexProduct)
            parameters.append(PLEX_IDENTITY, plexIdentity)

            extraUrlConfig()
        }
        header(HttpHeaders.Accept, "application/json")
    }

    private companion object {
        val PROTOCOL = URLProtocol.HTTPS
        const val HOST = "plex.tv"
        const val PLEX_PRODUCT = "X-Plex-Product"
        const val PLEX_IDENTITY = "X-Plex-Client-Identifier"
        const val PLEX_TOKEN = "X-Plex-Token"
    }
}