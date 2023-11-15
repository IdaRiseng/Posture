package no.sporty.posture.activities.setMovementCount


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import no.sporty.posture.R
import no.sporty.posture.model.Exercise
import no.sporty.posture.model.ExerciseRepetitionsLength
import no.sporty.posture.model.ExerciseTimeLength
import no.sporty.posture.model.WorkoutSetting
import no.sporty.posture.model.WorkoutSettingOption
import no.sporty.posture.ui.theme.PostureTheme
import no.sporty.posture.ui.theme.button.OnPrimaryOutlineButton
import no.sporty.posture.ui.theme.checkbox.CheckboxText
import no.sporty.posture.ui.theme.text.TitleAlwaysWhiteText

@Composable
fun SetMovementCount(
    exercise: Exercise,
    workoutSetting: WorkoutSetting,
    onContinueTimeClicked: (ExerciseTimeLength) -> Unit,
    onContinueRepClicked: (ExerciseRepetitionsLength) -> Unit,
) {
    PostureTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (workoutSetting.workoutSettingOption == WorkoutSettingOption.TIME_BASED) {
                SelectTimeLength(exercise, onContinueTimeClicked)
            } else {
                SelectRepLength(exercise, onContinueRepClicked)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SelectTimeLength(exercise: Exercise, onContinueTimeClicked: (ExerciseTimeLength) -> Unit) {
    var length by remember { mutableStateOf(ExerciseTimeLength.TWO_MINUTES) }
    TitleAlwaysWhiteText(textRes = R.string.how_long_exercise)
    FlowRow {
        ExerciseTimeLength.values().forEach {
            CheckboxText(
                text = "${it.length} minutes",
                checked = length == it,
                enabled = it.length - 1 <= exercise.movements.size,
                padding = PaddingValues(horizontal = 4.dp, vertical = 16.dp),
                onCheckedChange = { length = it }
            )
        }
    }
    OnPrimaryOutlineButton(onClick = { onContinueTimeClicked(length) }, textRes = R.string._continue)

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SelectRepLength(exercise: Exercise, onContinueRepClicked: (ExerciseRepetitionsLength) -> Unit) {
    var length by remember { mutableStateOf(ExerciseRepetitionsLength.TWO_REPS) }
    TitleAlwaysWhiteText(textRes = R.string.how_many_movements)
    FlowRow {
        ExerciseRepetitionsLength.values().forEach {
            CheckboxText(
                text = "${it.length} moves",
                checked = length == it,
                enabled = it.length - 1 <= exercise.movements.size,
                padding = PaddingValues(horizontal = 4.dp, vertical = 16.dp),
                onCheckedChange = { length = it }
            )
        }
    }
    OnPrimaryOutlineButton(onClick = { onContinueRepClicked(length) }, textRes = R.string._continue)
}