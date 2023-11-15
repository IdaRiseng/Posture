package no.sporty.posture.activities.settings

import no.sporty.posture.ui.theme.tabs.Tab
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import no.sporty.posture.R
import no.sporty.posture.ui.theme.scaffold.PostureTopBarScaffold

@Composable
fun Settings(
    onBackPressed: () -> Unit
) {
    PostureTopBarScaffold(onBackPressed, title = stringResource(id =R.string.settings)) {
        Column(horizontalAlignment = CenterHorizontally) {
            var selectedItem by remember { mutableIntStateOf(0) }
            Tab(
                modifier = Modifier.padding(top = 16.dp),
                selectedItemIndex = selectedItem,
                items = listOf("General", "Workout"),
                onClick = { selectedItem = it }
            )
            if (selectedItem == 0) {
                SettingsGeneral(
                    onDarkModeClicked = {},
                    onLanguageClicked = {},
                    onIdeasSent = {},
                    onTroubleSent = {}
                )
            } else {
                SettingsWorkout()
            }
        }
    }
}
