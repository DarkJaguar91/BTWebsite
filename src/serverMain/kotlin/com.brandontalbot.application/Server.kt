package com.brandontalbot.application

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import kotlinx.html.*
import java.io.File

fun HTML.index() {
    head {
        title("Brandon Talbot")
    }
    body {
        div {
            id = "root"
        }
        script(src = "/static/BTWeb.js") {}
    }
}

fun main() {
    embeddedServer(Netty, port = 8090, host = "127.0.0.1") {
        routing {
            get("/") {
                call.respondHtml(HttpStatusCode.OK, HTML::index)
            }
            staticFiles("/static", File(System.getenv("WEB_DIR")))
        }
    }.start(wait = true)
}