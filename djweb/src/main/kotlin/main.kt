import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.css.*
import react.dom.render
import series.seriesList

fun main() {
    window.onload = {
        render(document.getElementById("root")) {
            seriesList()
        }
    }
}
