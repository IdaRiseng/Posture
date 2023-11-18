package no.sporty.posture.ui.theme.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import no.sporty.posture.R
import no.sporty.posture.ui.theme.TextRadioButton
import no.sporty.posture.ui.theme.text.BodyBlackText
import no.sporty.posture.ui.theme.text.HeadlineBlackText


@Composable
fun <T> RadioGroupDialog(
    title: String,
    items: List<Pair<String, T>>,
    preselected: T,
    onSave: (T) -> Unit,
    onDismiss: () -> Unit
) {
    var selected by rememberSaveable { mutableStateOf(preselected) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.small
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                HeadlineBlackText(text = title)

                Spacer(modifier = Modifier.height(16.dp))
                items.forEach { (name, value) ->
                    TextRadioButton(
                        text = name,
                        selected = value == selected,
                        onSelect = { selected = value }
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    FilledTonalButton(
                        onClick = {
                            onDismiss.invoke()
                            onSave.invoke(selected)
                        }) {
                        BodyBlackText(text = stringResource(id = R.string.save))
                    }
                }
            }
        }
    }
}