package com.brandontalbot.website.components.common

import react.RBuilder
import react.RProps
import react.child
import react.functionalComponent
import styled.css
import styled.styledDiv

fun RBuilder.spinner(
    centered: Boolean = false
) = child(spinner) {
    attrs.centered = centered
}

private interface SpinnerProps : RProps {
    var centered: Boolean
}

private val spinner = functionalComponent<SpinnerProps> { props ->
    styledDiv {
        css {
            +SpinnerStyles.spinner
            if (props.centered) +SpinnerStyles.centered
        }
    }
}
