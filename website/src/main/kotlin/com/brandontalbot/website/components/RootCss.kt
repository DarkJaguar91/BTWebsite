package com.brandontalbot.website.components

import com.brandontalbot.website.constant.Colors
import kotlinx.css.*
import kotlinx.css.properties.Timing
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import styled.StyleSheet
import styled.injectGlobal

private val htmlCss = CSSBuilder().apply {
    html {
        body {
            backgroundColor = Colors.background
            margin(0.px)
            padding(0.px)

            child(".example-enter") {
                opacity = 0.01
            }

            child(".example-enter.example-enter-active") {
                opacity = 1
                transition("opacity", 500.ms, Timing.easeIn)
            }

            child(".example-leave") {
                opacity = 1
            }

            child(".example-leave.example-leave-active") {
                opacity = 0.01
                transition("opacity", 300.ms, Timing.easeIn)
            }
        }
    }
}

object RootStyles : StyleSheet("Brandontalbot.com") {
    fun injectGlobalCss() {
        injectGlobal(htmlCss.toString())
    }
}
