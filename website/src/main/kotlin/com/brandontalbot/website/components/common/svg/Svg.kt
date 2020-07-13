package com.brandontalbot.website.components.common.svg

import kotlinx.css.Color
import kotlinx.html.SVG
import kotlinx.html.Tag
import react.dom.RDOMBuilder
import kotlin.reflect.KProperty

var RDOMBuilder<SVG>.width by Attributes<Int>()
var RDOMBuilder<SVG>.height by Attributes<Int>()

fun RDOMBuilder<SVG>.viewBox(x: Int, y: Int, width: Int, height: Int) =
    setProp("viewBox", "$x $y $width $height")

interface SvgTag : Tag

var RDOMBuilder<SvgTag>.fill by Attributes<Color>()
var RDOMBuilder<SvgTag>.stroke by Attributes<Color>()
var RDOMBuilder<SvgTag>.strokeSize by Attributes<Color>("stroke-size")

class TransformBuilder {
    internal val transforms = mutableListOf<String>()

    fun rotate(angle: Int, x: Int? = null, y: Int? = null) = transforms.add(
        if (x != null || y != null)
            "rotate($angle, ${x!!} ${y!!}"
        else
            "rotate($angle)"
    )

    fun translate(x: Int, y: Int) = transforms.add(
        "translate($x, $y)"
    )
}

fun RDOMBuilder<SvgTag>.transform(block: TransformBuilder.() -> Unit) =
    setProp("transform", TransformBuilder().apply(block).transforms.joinToString(separator = " "))

class Attributes<T : Any>(
    private val propName: String? = null
) {
    operator fun getValue(thisRef: RDOMBuilder<*>, property: KProperty<*>): T? = error("Only get available on props.")

    operator fun setValue(thisRef: RDOMBuilder<*>, property: KProperty<*>, value: T?) {
        thisRef.setProp(propName ?: property.name, value)
    }
}
