package no.sporty.posture.extensions

import java.text.DecimalFormat

fun Float.formatZeros(): String {
    val format = DecimalFormat("0.#")
    return format.format(this)
}