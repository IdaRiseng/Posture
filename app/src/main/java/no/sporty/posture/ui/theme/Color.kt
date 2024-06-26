package no.sporty.posture.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val lighterSignature = Color(0xFFF8F0EE)
private val lightSignature = Color(0xFFEBAFA1)
private val signature = Color(0xFFB8614E)
private val darkSignature = Color(0xFF864435)
private val darkerSignature = Color(0xFF544A47)

private val white = Color(0xFFFFFFFF)
private val grey = Color(0xFFEDEDED)
private val darkGrey = Color(0xFF3E3E3E)
private val darkerGrey = Color(0xFF222222)
private val black = Color(0xFF000000)

object PostureColor {
    fun getLightTheme() = lightColorScheme(
        primary = signature,
        onPrimary = white,
        primaryContainer = lightSignature, //selectable selected card
        secondary = darkSignature,
        tertiary = white, // normal cards
        onTertiary = black,
        background = lighterSignature,
        onBackground = black,
        surface = lighterSignature, //topbar color

        //For dialog
        secondaryContainer = lighterSignature, // dialog flat button
        surfaceVariant = lighterSignature, //Dialog!!!
        outline = signature, //clicked radio button
        outlineVariant = darkSignature, //not clicked radio button
    )

    fun getDarkTheme() = darkColorScheme(
        primary = signature,
        onPrimary = white,
        primaryContainer = darkGrey,
        secondary = lightSignature,
        tertiary = darkerSignature,
        onTertiary = white,
        background = darkerGrey,
        onBackground = white,
        surface = darkerGrey,


        //For dialog
        secondaryContainer = darkerSignature, // dialog flat button
        surfaceVariant = darkerSignature, //Dialog!!!
        outline = signature, //clicked radio button
        outlineVariant = lightSignature, //not clicked radio button
    )
}
