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
    onIdeasClicked: () -> Unit,
    onTroubleClicked: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        SmallDisabledBlackText(textRes = R.string.general)
        ExpandableTextButton(textRes = R.string.darkmode, iconId = R.drawable.ic_darkmode, onClick = onDarkModeClicked)

        Spacer(modifier = Modifier.height(32.dp))
        SmallDisabledBlackText(text = "Feedback")
        ExpandableTextButton(
            textRes = R.string.got_ideas,
            iconId = R.drawable.ic_lightbulb,
            onClick = onIdeasClicked
        )
        ExpandableTextButton(
            textRes = R.string.trouble_with_app,
            iconId = R.drawable.ic_warning_triangle,
            onClick = onTroubleClicked
        )
    }
}