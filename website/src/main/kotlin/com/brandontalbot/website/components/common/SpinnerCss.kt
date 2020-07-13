package com.brandontalbot.website.components.common

import kotlinx.css.Position
import kotlinx.css.border
import kotlinx.css.borderRadius
import kotlinx.css.borderTop
import kotlinx.css.height
import kotlinx.css.left
import kotlinx.css.margin
import kotlinx.css.pct
import kotlinx.css.position
import kotlinx.css.properties.IterationCount
import kotlinx.css.properties.Time
import kotlinx.css.properties.Timing
import kotlinx.css.properties.deg
import kotlinx.css.properties.rotate
import kotlinx.css.properties.transform
import kotlinx.css.properties.translate
import kotlinx.css.px
import kotlinx.css.right
import kotlinx.css.top
import kotlinx.css.width
import kotlinx.css.zIndex
import styled.StyleSheet
import styled.animation

object SpinnerStyles : StyleSheet("Spinner") {
    val spinner by css {
        border = "16px solid #f3f3f3"
        borderTop = "16px solid #292929"
        borderRadius = 50.pct
        width = 120.px
        height = 120.px

        animation(
            duration = Time("2s"),
            timing = Timing.linear,
            iterationCount = IterationCount.infinite
        ) {
            0 {
                transform {
                    rotate(0.deg)
                }
            }
            100 {
                transform {
                    rotate(360.deg)
                }
            }
        }
    }

    val centered by css {
        position = Position.fixed
        top = 50.pct
        left = 50.pct
        zIndex = 1

        margin(top = (-60).px, left = (-60).px)
    }
}
