package config

import Plex.PlexClient
import Plex.exception.PinExpiredException
import Plex.exception.UserTokenInvalidException
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

open class PlexConfig {
    open val plexProduct: String = "BrandonTalbot.com"

    open val plexIdentity: String = "ecdd7ebf-8db8-4721-8cb7-78937b309f99"

    open val httpClient: HttpClient by lazy {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
                expectSuccess = true
                HttpResponseValidator {
                    handleResponseExceptionWithRequest { cause, request ->
                        when(cause) {
                            is ClientRequestException -> {
                                when(cause.response.status) {
                                    HttpStatusCode.NotFound -> throw PinExpiredException()
                                    HttpStatusCode.Unauthorized -> throw UserTokenInvalidException()
                                }
                            }
                        }
                    }
                }
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }

    open val plexClient: PlexClient by lazy {
        PlexClient(
            plexProduct,
            plexIdentity,
            httpClient,
        )
    }
}
