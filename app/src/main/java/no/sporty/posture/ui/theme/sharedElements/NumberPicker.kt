package no.sporty.posture.ui.theme.sharedElements

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import no.sporty.posture.ui.theme.text.BodyBlackText

@Composable
fun NumberPicker(number: MutableState<Int>, text: String = "${number.value}", onNumberChanged: (Int) -> Unit = {}) {
    val iconColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp))
            .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = {
                if (number.value > 1) {
                    number.value -= 1
                    onNumberChanged(number.value)
                }
            }) {
            Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "less", tint = iconColor)
        }

        Box(Modifier.weight(2f), contentAlignment = Alignment.Center) {
            BodyBlackText(text = text)
        }

        IconButton(
            modifier = Modifier.weight(1f),
            onClick = {
                if (number.value < 60) {
                    number.value += 1
                    onNumberChanged(number.value)
                }
            }) {
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = "more", tint = iconColor)
        }
    }
}