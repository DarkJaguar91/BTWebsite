package com.brandontalbot.server.route

import com.brandontalbot.server.route.sonarr.sonarr
import com.brandontalbot.server.route.tvdb.tvdb
import com.brandontalbot.server.route.web.website
import io.ktor.application.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.*
import io.ktor.util.pipeline.*
import io.ktor.utils.io.*

fun Application.routing() {
    routing {
        website()
        tvdb()
        sonarr()
    }
}

suspend fun PipelineContext<*, ApplicationCall>.proxyResponse(response: HttpResponse) {
    val proxiedHeaders = response.headers
    val contentType = proxiedHeaders[HttpHeaders.ContentType]
    val contentLength = proxiedHeaders[HttpHeaders.ContentLength]

    // Proxy the request forward
    call.respond(object : OutgoingContent.WriteChannelContent() {
        override val contentLength: Long? = contentLength?.toLong()
        override val contentType: ContentType? = contentType?.let { ContentType.parse(it) }
        override val headers: Headers = Headers.build {
            appendAll(proxiedHeaders.filter { key, _ ->
                !key.equals(
                    HttpHeaders.ContentType,
                    ignoreCase = true
                ) && !key.equals(HttpHeaders.ContentLength, ignoreCase = true)
                        && !key.equals(HttpHeaders.TransferEncoding, ignoreCase = true)
            })
        }
        override val status: HttpStatusCode? = response.status
        override suspend fun writeTo(channel: ByteWriteChannel) {
            response.content.copyAndClose(channel)
        }
    })
}
