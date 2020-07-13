package com.brandontalbot.website

import com.brandontalbot.website.components.root
import react.dom.render
import kotlin.browser.document
import kotlin.browser.window

fun main() {
    window.onload = {
        render(document.getElementById("root")) {
            root()
        }
    }
}
