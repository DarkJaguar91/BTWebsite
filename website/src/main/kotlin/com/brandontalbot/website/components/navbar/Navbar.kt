package com.brandontalbot.website.components.navbar

import react.RBuilder
import react.RProps
import react.child
import react.dom.li
import react.functionalComponent
import react.router.dom.navLink
import styled.css
import styled.styledUl

fun RBuilder.navbar() = child(navbar) {

}

private val navbar = functionalComponent<RProps> {
    styledUl {
        css {
            +NavbarStyles.navbar
        }

        li {
            navLink<RProps>(to = "/", exact = true) { +"Series List" }
        }
        li {
            navLink<RProps>(to = "/series/add") { +"Add Series" }
        }
    }
}