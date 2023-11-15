package no.sporty.posture.ui.theme.sharedElements

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import no.sporty.posture.ui.theme.text.BodyBlackText


@Composable
fun AlertDialog(showAlert: MutableState<Boolean>, onExit: () -> Unit) {
    if (showAlert.value) {
        androidx.compose.material3.AlertDialog(
            text = { BodyBlackText(text = "Do you really want to quit?") },
            confirmButton = {
                Button(onClick = { onExit() }) {
                    Text(text = "Quit")
                }
            },
            dismissButton = {
                Button(onClick = { onExit() }) {
                    Text(text = "No")
                }
            },
            onDismissRequest = { showAlert.value = false },
        )
    }
}