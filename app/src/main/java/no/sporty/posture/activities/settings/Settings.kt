package no.sporty.posture.activities.settings

import androidx.appcompat.app.AppCompatDelegate
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import no.sporty.posture.R
import no.sporty.posture.sharedPreferences.ThemePrefs
import no.sporty.posture.ui.theme.dialogs.RadioGroupDialog
import no.sporty.posture.ui.theme.scaffold.PostureTopBarScaffold
import no.sporty.posture.ui.theme.tabs.Tab


@Composable
fun Settings(
    onBackPressed: () -> Unit,
    onCustomExerciseClicked: () -> Unit,
    onThemeSelected: (Int) -> Unit,
    onSendEmail: (Boolean) -> Unit,
) {
    var isDarkmodeDialogVisible by remember { mutableStateOf(false) }

    PostureTopBarScaffold(onBackPressed, title = stringResource(id = R.string.settings)) {
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
                    onDarkModeClicked = { isDarkmodeDialogVisible = true },
                    onIdeasClicked = { onSendEmail(false) },
                    onTroubleClicked = { onSendEmail(true) }
                )
            } else {
                SettingsWorkout(
                    onCustomExerciseClicked = onCustomExerciseClicked,
                )
            }
        }

        if (isDarkmodeDialogVisible)
            DarkmodeDialog(
                onThemeSelected = onThemeSelected,
                onDismissDialog = { isDarkmodeDialogVisible = false }
            )
    }
}

@Composable
fun DarkmodeDialog(onThemeSelected: (Int) -> Unit, onDismissDialog: () -> Unit) {
    val selectedTheme = ThemePrefs.readSelectedTheme(LocalContext.current) ?: AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    val themes = listOf(
        stringResource(id = R.string.device_theme) to AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM,
        stringResource(id = R.string.light_theme) to AppCompatDelegate.MODE_NIGHT_NO,
        stringResource(id = R.string.dark_theme) to AppCompatDelegate.MODE_NIGHT_YES
    )

    RadioGroupDialog(
        title = stringResource(id = R.string.dark_theme_title),
        items = themes,
        preselected = selectedTheme,
        onSave = onThemeSelected,
        onDismiss = onDismissDialog
    )
}
