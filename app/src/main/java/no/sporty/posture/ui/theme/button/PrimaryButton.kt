package no.sporty.posture.ui.theme.button

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import no.sporty.posture.ui.theme.text.BodyAlwaysWhiteText

@Composable
fun PrimaryButton(onClick: () -> Unit, @StringRes textRes: Int, modifier: Modifier = Modifier) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(16.dp),
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        BodyAlwaysWhiteText(textRes = textRes)
    }
}