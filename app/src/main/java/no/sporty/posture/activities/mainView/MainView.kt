package no.sporty.posture.activities.mainView

import SmallDisabledBlackText
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import no.sporty.posture.R
import no.sporty.posture.activities.mainView.elements.MainTopBar
import no.sporty.posture.extensions.overScrollColor
import no.sporty.posture.model.CustomExercise
import no.sporty.posture.model.Exercise
import no.sporty.posture.model.TopBarInfo
import no.sporty.posture.sharedPreferences.CustomExercisePrefs
import no.sporty.posture.ui.theme.PostureTheme
import no.sporty.posture.ui.theme.cards.ButtonInfo
import no.sporty.posture.ui.theme.cards.CustomExerciseCard
import no.sporty.posture.ui.theme.cards.ExerciseCard
import no.sporty.posture.ui.theme.cards.InfoCard
import no.sporty.posture.ui.theme.text.HeadlineBlackText

@Composable
fun MainView(
    topBarInfo: TopBarInfo,
    exercises: List<Exercise>,
    customExercises: List<CustomExercise>,
    onSettingsClicked: () -> Unit,
    onExerciseClicked: (Exercise) -> Unit,
    onCreateCustomExerciseClicked: () -> Unit,
    onCustomExerciseClicked: (CustomExercise) -> Unit
) {
    val scrollState = rememberScrollState()
    PostureTheme {
        Column(
            Modifier
                .overScrollColor(scrollState, MaterialTheme.colorScheme.primary)
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            MainTopBar(topBarInfo, onSettingsClicked)
            Column(Modifier.padding(16.dp)) {
                Info(onCreateCustomExerciseClicked)
                CustomExercises(customExercises, onCustomExerciseClicked)
                Exercises(exercises, onExerciseClicked)

                Column(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SmallDisabledBlackText(text = "Remember to listen to your body and stop if you need to") //TODO
                }
            }
        }
    }
}


@Composable
private fun Info(onButtonClicked: () -> Unit) {
    HeadlineBlackText(textRes = R.string.info, padding = PaddingValues(vertical = 16.dp))
    InfoCard(
        title = R.string.info_thank_you_title,
        desc = R.string.info_thank_you_desc,
        icon = R.drawable.launcher_icon_round
    )
    InfoCard(
        title = R.string.info_custom_exercise_title,
        desc = R.string.info_custom_exercise_desc,
        buttonInfo = ButtonInfo(onButtonClicked, R.string.create),
        image = R.drawable.launcher_icon
    )
}

@Composable
private fun Exercises(exercises: List<Exercise>, onExerciseClicked: (Exercise) -> Unit) {
    HeadlineBlackText(textRes = R.string.exercises, padding = PaddingValues(vertical = 16.dp))
    exercises.forEach {
        ExerciseCard(it, onExerciseClicked)
    }
}

@Composable
private fun CustomExercises(customExercises: List<CustomExercise>,onCustomExerciseClicked: (CustomExercise) -> Unit) {
    if (customExercises.isNotEmpty()) {
        HeadlineBlackText(textRes = R.string.custom_exercise, padding = PaddingValues(vertical = 16.dp))
        customExercises.forEach {
            CustomExerciseCard(it, onCustomExerciseClicked)
        }
    }
}