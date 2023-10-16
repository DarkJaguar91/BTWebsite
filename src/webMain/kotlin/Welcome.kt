import Plex.PlexClient
import Plex.model.PlexPin
import Plex.model.PlexUser
import io.ktor.http.*
import kotlinx.browser.localStorage
import kotlinx.browser.window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.w3c.dom.get
import org.w3c.dom.set
import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.useEffect
import react.useState

external interface WelcomeProps : Props {
    var plexClient: PlexClient
}

val Welcome = FC<WelcomeProps> { props ->
    val (pin, setPin) = useState {
        localStorage["PLEX_PIN"]?.let { Json.decodeFromString<PlexPin>(it) }
    }
    val (user, setUser) = useState {
        localStorage["PLEX_USER"]?.let { Json.decodeFromString<PlexUser>(it) }
    }

    useEffect(pin) {
        pin?.also {
            GlobalScope.launch {
                val pinStatus = try {
                    props.plexClient.checkPin(pin)
                } catch (e: Exception) {
                    println("here")
                    props.plexClient.generatePin().also {
                        localStorage["PLEX_PIN"] = Json.encodeToString(it)
                        setPin(it)
                    }
                    return@launch
                }

                println("here2")
                pinStatus.authToken?.let {
                    props.plexClient.getUser(it).also {
                        localStorage["PLEX_USER"] = Json.encodeToString(it)
                        setUser(it)
                    }
                } ?: run {
                    val url = URLBuilder().apply {
                        protocol = URLProtocol.HTTPS
                        host = "app.plex.tv"
                        path("auth#")

                        parameters.append("clientID", "ecdd7ebf-8db8-4721-8cb7-78937b309f99")
                        parameters.append("code", pin.code)
                        parameters.append("forwardUrl", window.location.toString())
                        parameters.append("context[device][product]", "BrandonTalbot.com")
                    }.build()

                    window.location.href = url.toString().replace("auth%23", "auth#")
                }
            }
        }
    }

    button {
        +"Click Me Plexy"
        onClick = {
            GlobalScope.launch {
                props.plexClient.generatePin().also {
                    localStorage["PLEX_PIN"] = Json.encodeToString(it)
                    setPin(it)
                }
            }
        }
    }
}
