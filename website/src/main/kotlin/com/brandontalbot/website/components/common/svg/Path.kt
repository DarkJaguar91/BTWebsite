package com.brandontalbot.website.components.common.svg

import kotlinx.css.Color
import kotlinx.html.*
import react.dom.RDOMBuilder
import react.dom.tag
import styled.StyledDOMBuilder
import styled.styledTag

fun RDOMBuilder<SVG>.path(classes: String? = null, block: RDOMBuilder<Path>.() -> Unit) = tag(block) {
    Path(attributesMapOf("classes", classes), it)
}

fun RDOMBuilder<Group>.path(classes: String? = null, block: RDOMBuilder<Path>.() -> Unit) = tag(block) {
    Path(attributesMapOf("classes", classes), it)
}

fun RDOMBuilder<SVG>.styledPath(block: StyledDOMBuilder<Path>.() -> Unit) = styledTag(block) {
    Path(emptyMap, it)
}

fun RDOMBuilder<Group>.styledPath(block: StyledDOMBuilder<Path>.() -> Unit) = styledTag(block) {
    Path(emptyMap, it)
}

class Path(initialAttributes: Map<String, String>, override val consumer: TagConsumer<*>) :
    HTMLTag("path", consumer, initialAttributes, null, false, true),
    HtmlBlockInlineTag, SvgTag

var RDOMBuilder<Path>.d by Attributes<String>()
var RDOMBuilder<Path>.fill by Attributes<Color>()

fun RDOMBuilder<Path>.d(block: PathBuilder.() -> Unit) {
    d = PathBuilder().apply(block).d
}

class PathBuilder {
    internal var d: String = ""

    fun M(x: Int, y: Int) = append("M $x $y")
    fun m(dX: Int, dY: Int) = append("m $dX $dY")

    fun L(x: Int, y: Int) = append("L $x $y")
    fun l(dX: Int, dY: Int) = append("l $dX $dY")

    fun H(x: Int) = append("H $x")
    fun h(dX: Int) = append("h $dX")

    fun V(y: Int) = append("V $y")
    fun v(dY: Int) = append("v $dY")

    fun C(x1: Int, y1: Int, x2: Int, y2: Int, x: Int, y: Int) = append("C $x1 $y1, $x2 $y2, $x $y")
    fun c(dX1: Int, dY1: Int, dX2: Int, dY2: Int, dX: Int, dY: Int) = append("C $dX1 $dY1, $dX2 $dY2, $dX $dY")

    fun Z() = append("Z")
    fun z() = append("z")

    private fun append(value: String) {
        d += if (d.isEmpty()) value else " $value"
    }
}