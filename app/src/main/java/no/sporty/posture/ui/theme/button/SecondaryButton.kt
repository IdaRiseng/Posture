package no.sporty.posture.ui.theme.button

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp


@Composable
fun SecondaryButton(onClick: () -> Unit, @StringRes textRes: Int, modifier: Modifier = Modifier) {
    OutlinedButton(
        shape = RoundedCornerShape(16.dp),
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(id = textRes))
    }
}
