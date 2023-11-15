package no.sporty.posture.extensions

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun Modifier.conditional(condition : Boolean, modifier : Modifier.() -> Modifier) : Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}


/**
 * Set the color of the overscroll area on top.
 */
@Composable
fun Modifier.overScrollColor(
    scrollState: ScrollState,
    color: Color
): Modifier {
    return drawWithContent {
        drawContent()
        val height = if (scrollState.canScrollBackward) 0.dp else 4.dp
        drawRoundRect(
            color = color,
            size = Size(this.size.width, height.toPx()),
        )
    }
}