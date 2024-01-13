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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
private fun TitleText(text: String, padding: PaddingValues = PaddingValues(), color: Color) {
    Text(
        text = text,
        modifier = Modifier.padding(padding),
        fontSize = 16.sp,
        color = color,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun TitleAlwaysWhiteText(text: String, padding: PaddingValues = PaddingValues()) {
    TitleText(text = text, padding = padding, color = Color.White)
}

@Composable
fun TitleAlwaysWhiteText(@StringRes textRes: Int, padding: PaddingValues = PaddingValues()) {
    TitleText(text = stringResource(id = textRes), padding = padding, color = Color.White)
}

@Composable
fun TitleBlackText(@StringRes textRes: Int, padding: PaddingValues = PaddingValues()) {
    TitleText(text = stringResource(id = textRes), padding = padding, color = MaterialTheme.colorScheme.onBackground)
}

@Composable
fun TitleBlackText(text: String, padding: PaddingValues = PaddingValues()) {
    TitleText(text = text, padding = padding, color = MaterialTheme.colorScheme.onBackground)
}