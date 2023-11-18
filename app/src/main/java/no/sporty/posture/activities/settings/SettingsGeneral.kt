package no.sporty.posture.activities.settings

import SmallDisabledBlackText
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import no.sporty.posture.R
import no.sporty.posture.ui.theme.button.ExpandableTextButton
import no.sporty.posture.ui.theme.button.PrimaryButton
import no.sporty.posture.ui.theme.inputField.InputField

@Composable
fun SettingsGeneral(
    onDarkModeClicked: () -> Unit,
    onCustomExerciseClicked: () -> Unit,
    onIdeasSent: () -> Unit,
    onTroubleSent: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        var expandedButton by remember { mutableStateOf<Int?>(null) }

        Spacer(modifier = Modifier.height(16.dp))
        SmallDisabledBlackText(textRes = R.string.general)
        ExpandableTextButton(textRes = R.string.darkmode, iconId = android.R.drawable.ic_delete, onClick = onDarkModeClicked)
        ExpandableTextButton(textRes = R.string.custom_exercise, iconId = android.R.drawable.ic_delete, onClick = onCustomExerciseClicked)


        Spacer(modifier = Modifier.height(32.dp))
        SmallDisabledBlackText(text = "Feedback")
        ExpandableTextButton(
            textRes = R.string.got_ideas,
            iconId = android.R.drawable.ic_delete,
            isSelected = expandedButton == R.string.got_ideas,
            onClick = {
                expandedButton = if (expandedButton == R.string.got_ideas) null else R.string.got_ideas
            }
        ) {
            FeedbackView(onIdeasSent)
        }
        ExpandableTextButton(
            textRes = R.string.trouble_with_app,
            iconId = android.R.drawable.ic_delete,
            isSelected = expandedButton == R.string.trouble_with_app,
            onClick = {
                expandedButton = if (expandedButton == R.string.trouble_with_app) null else R.string.trouble_with_app
            }
        ) {
            FeedbackView(onTroubleSent)
        }
    }
}


@Composable
private fun FeedbackView(onFeedbackSend: () -> Unit) {
    var value by remember { mutableStateOf("") }

    Column {
        InputField(value = value, onValueChange = { value = it })
        PrimaryButton(onClick = onFeedbackSend, textRes = R.string.send)
    }
}