package no.sporty.posture.ui.theme.text

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp

@Composable
private fun HeadlineText(text: String, color: Color, padding: PaddingValues = PaddingValues()) {
    Text(
        text = text,
        fontSize = 20.sp,
        color = color,
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun HeadlineBlackText(@StringRes textRes: Int, padding: PaddingValues = PaddingValues()) =
    HeadlineText(
        text = stringResource(id = textRes),
        color = MaterialTheme.colorScheme.onBackground,
        padding = padding
    )

@Composable
fun HeadlineBlackText(text: String, padding: PaddingValues = PaddingValues()) =
    HeadlineText(
        text = text,
        color = MaterialTheme.colorScheme.onBackground,
        padding = padding
    )

@Composable
fun HeadlineAlwaysWhiteText(text: String) = HeadlineText(text = text, color = Color.White)

@Composable
fun HeadlineAlwaysWhiteText(@StringRes textRes: Int) = HeadlineText(text = stringResource(id = textRes), color = Color.White)

