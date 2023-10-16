import config.WebContext
import kotlinx.browser.document
import react.create
import react.dom.client.createRoot

fun main() {
    val container = document.getElementById("root")!!

    createRoot(container).render(Welcome.create {
        plexClient = WebContext.plexConfig.plexClient
    })
}