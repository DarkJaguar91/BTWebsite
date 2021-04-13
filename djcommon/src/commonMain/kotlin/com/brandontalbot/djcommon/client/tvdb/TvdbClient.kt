package com.brandontalbot.djcommon.client.tvdb

import com.brandontalbot.djcommon.client.tvdb.model.Login
import com.brandontalbot.djcommon.client.tvdb.model.LoginResponse
import com.brandontalbot.djcommon.client.tvdb.model.SearchResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlin.time.ExperimentalTime
import kotlin.time.TimeMark
import kotlin.time.TimeSource

@OptIn(ExperimentalTime::class)
class TvDbClient(
    private val client: HttpClient,
    private val token: String,
    private val username: String,
    private val password: String,
    private val host: String = "thetvdb.com",
    private val port: Int = 443,
    private val protocol: URLProtocol = URLProtocol.HTTPS,
) {
    private var jwtToken: String? = null
    private var collectionDate: TimeMark? = null

    suspend fun search(seriesName: String) = get<SearchResponse>(
        "search", "series",
        params = listOf(
            "name" to seriesName
        )
    ).let { resposnse ->
        resposnse.copy(data = resposnse.data.map { series ->
            series.copy(
                image = series.image?.let { redirectUrl(it) },
                poster = series.poster?.let { redirectUrl(it) },
                banner = series.banner?.let { redirectUrl(it) }
            )
        })
    }

    private fun redirectUrl(url: String) =
        "${protocol.name}://${host.removePrefix("api.")}${url}"

    private suspend inline fun <reified T> get(
        vararg path: String,
        params: List<Pair<String, Any>>? = null,
    ): T = withJwtToken {
        client.get {
            url {
                this.host = "api.${this@TvDbClient.host.removePrefix("api.")}"
                this.port = this@TvDbClient.port
                this.protocol = this@TvDbClient.protocol
                this.path(*path)
                params?.forEach {
                    parameters.append(it.first, it.second.toString())
                }
            }
            header("Authorization", "Bearer $it")
        }
    }

    private suspend inline fun <reified T> withJwtToken(block: (String) -> T): T {
        if (collectionDate?.let { it.elapsedNow().inHours > 24 } != false) {
            client.post<LoginResponse> {
                url {
                    this.host = "api.${this@TvDbClient.host.removePrefix("api.")}"
                    this.port = this@TvDbClient.port
                    this.protocol = this@TvDbClient.protocol
                    path("login")
                }
                body = Login(token, username, password)
                contentType(ContentType.Application.Json)
            }.let {
                jwtToken = it.token
                collectionDate = TimeSource.Monotonic.markNow()
            }
        } else if (collectionDate!!.elapsedNow().inHours > 16) {
            client.get<LoginResponse> {
                url {
                    this.host = "api.${this@TvDbClient.host.removePrefix("api.")}"
                    this.port = this@TvDbClient.port
                    this.protocol = this@TvDbClient.protocol
                    path("refresh_token")
                }
                header("Authorization", "Bearer $jwtToken")
            }.let {
                jwtToken = it.token
                collectionDate = TimeSource.Monotonic.markNow()
            }
        }

        return block(jwtToken!!)
    }
}
