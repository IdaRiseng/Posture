package no.sporty.posture.ui.theme.button

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import no.sporty.posture.ui.theme.text.BodyAlwaysWhiteText

@Composable
fun OnPrimaryOutlineButton(@StringRes textRes: Int, onClick: () -> Unit) {
    OutlinedButton(
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimary),
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick) {
        BodyAlwaysWhiteText(textRes)
    }
}