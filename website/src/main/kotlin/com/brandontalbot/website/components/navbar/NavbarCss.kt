package com.brandontalbot.website.components.navbar

import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import styled.StyleSheet

object NavbarStyles: StyleSheet("Navbar") {
    val navbar by css {
        listStyleType = ListStyleType.none
        margin(top = 0.px, bottom = 16.px, left = 0.px, right = 0.px)
        padding(0.px)
        overflow = Overflow.hidden
        backgroundColor = Color("#333")

        child("li") {
            float = Float.left

            child("a") {
                display = Display.block
                color = Color.white
                textAlign = TextAlign.center
                padding(vertical = 14.px,  horizontal = 16.px)
                textDecoration = TextDecoration.none
            }

            child("a:hover:not(.active)") {
                backgroundColor = Color.black
            }

            child("a.active") {
                backgroundColor = Color.darkGreen
            }
        }
    }
}