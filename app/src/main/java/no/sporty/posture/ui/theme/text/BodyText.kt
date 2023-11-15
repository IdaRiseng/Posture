package no.sporty.posture.ui.theme.text

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
private fun BodyText(text: String, color: Color, textAlign: TextAlign? = null) {
    Text(
        text = text,
        fontSize = 16.sp,
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun BodyBlackText(@StringRes textRes: Int, textAlign: TextAlign? = null) {
    BodyText(text = stringResource(id = textRes), color = MaterialTheme.colorScheme.onBackground, textAlign)
}

@Composable
fun BodyBlackText(text: String) {
    BodyText(text = text, color = MaterialTheme.colorScheme.onBackground)
}

@Composable
fun BodyAlwaysWhiteText(@StringRes textRes: Int) {
    BodyText(text = stringResource(id = textRes), color = Color.White)
}

@Composable
fun BodyAlwaysWhiteText(text: String) {
    BodyText(text = text, color = Color.White)
}