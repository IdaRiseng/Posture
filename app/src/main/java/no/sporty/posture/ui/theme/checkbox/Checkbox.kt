package no.sporty.posture.ui.theme.checkbox

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CheckboxText(
    text: String,
    checked: Boolean,
    enabled: Boolean,
    padding: PaddingValues = PaddingValues(),
    onCheckedChange: () -> Unit
) {
    if (checked) {
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier.padding(padding),
            onClick = onCheckedChange,
            enabled = enabled,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            Text(text = text)
        }
    } else {
        OutlinedButton(
            colors = ButtonDefaults.buttonColors(contentColor = Color.White),
            modifier = Modifier.padding(padding),
            onClick = onCheckedChange,
            enabled = enabled,
            border = BorderStroke(1.dp, if (enabled) Color.White else MaterialTheme.colorScheme.secondary),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            Text(text = text)
        }
    }
}