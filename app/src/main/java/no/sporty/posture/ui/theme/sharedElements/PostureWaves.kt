package no.sporty.posture.ui.theme.sharedElements

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.math.PI
import kotlin.math.cos

@Composable
fun PostureWave(modifier: Modifier = Modifier){
    Column(modifier.height(16.dp)) {
        val color = MaterialTheme.colorScheme.background
        HorizontalWave(
            phase = rememberPhaseState(0f),
            alpha = 1f,
            amplitude = 50f,
            frequency = 0.5f,
            color = color
        )
        HorizontalWave(
            phase = rememberPhaseState(15f),
            alpha = 0.5f,
            amplitude = 80f,
            frequency = 0.3f,
            color = color
        )
        HorizontalWave(
            phase = rememberPhaseState(10f),
            alpha = 0.2f,
            amplitude = 40f,
            frequency = 0.6f,
            color = color
        )
    }
}

@Composable
private fun HorizontalWave(
    phase: MutableState<Float>,
    alpha: Float,
    amplitude: Float,
    frequency: Float,
    color: Color
) {
    Canvas(
        modifier = Modifier.fillMaxWidth(),
        onDraw = {
            val wavePath = Path()
            val centerY = size.height / 2f
            var x = 0

            wavePath.moveTo(0f, centerY + amplitude)

            while (x < size.width.toInt()) {
                val y = centerY + amplitude * cos(2 * PI * frequency * x / size.width + phase.value)
                wavePath.lineTo(x.toFloat(), y.toFloat())
                x++
            }
            wavePath.lineTo(x.toFloat(), centerY + amplitude)

            drawPath(
                path = wavePath,
                color = color,
                alpha = alpha,
                style = Fill
            )
        }
    )
}

@Composable
private fun rememberPhaseState(startPosition: Float): MutableState<Float> {
    val step: Long = 100
    val phase = remember { mutableStateOf(startPosition) }
    LaunchedEffect(phase, step) {
        while (isActive) {
            phase.value = phase.value + 0.02f
            delay(step)
        }
    }
    return phase
}




