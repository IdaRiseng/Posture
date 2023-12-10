package no.sporty.posture.activities.settings

import SmallDisabledBlackText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.unit.dp
import no.sporty.posture.R
import no.sporty.posture.model.WorkoutSetting
import no.sporty.posture.model.WorkoutSettingOption
import no.sporty.posture.sharedPreferences.WorkoutSettingPrefs
import no.sporty.posture.ui.theme.button.ExpandableTextButton
import no.sporty.posture.ui.theme.button.PrimaryButton
import no.sporty.posture.ui.theme.sharedElements.NumberPicker
import no.sporty.posture.ui.theme.text.BodyBlackText

@Composable
fun SettingsWorkout(
    onCustomExerciseClicked: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val context = LocalContext.current
        var workoutSetting by remember { mutableStateOf(WorkoutSettingPrefs.getWorkoutOption(context)) }
        val timeBasedLength by remember { mutableIntStateOf(WorkoutSettingPrefs.getTimeBasedWorkout(context)) }
        val repBasedLength by remember { mutableIntStateOf(WorkoutSettingPrefs.getRepBasedWorkout(context)) }

        Spacer(modifier = Modifier.height(16.dp))
        SmallDisabledBlackText(textRes = R.string.general)
        ExpandableTextButton(textRes = R.string.custom_exercise, iconId = R.drawable.ic_customize, onClick = onCustomExerciseClicked)

        Spacer(modifier = Modifier.height(16.dp))
        SmallDisabledBlackText(textRes = R.string.options)
        ExpandableTextButton(
            textRes = R.string.time_based,
            iconId = R.drawable.ic_timer,
            isSelected = workoutSetting.workoutSettingOption == WorkoutSettingOption.TIME_BASED,
            showCheckBox = true,
            onClick = {
                workoutSetting = workoutSetting.copy(workoutSettingOption = WorkoutSettingOption.TIME_BASED)
                WorkoutSettingPrefs.saveWorkoutOption(context, workoutSetting)
            },
            content = {
                SettingsWorkoutExpanded(
                    textRes = R.string.how_long_movement,
                    startNumber = timeBasedLength,
                    endText = R.plurals.minute,
                    onClick = {
                        Toast.makeText(context, "Workout saved", Toast.LENGTH_LONG).show()
                        WorkoutSettingPrefs.saveTimeBasedWorkout(context, it)
                    }
                )
            }
        )
        ExpandableTextButton(
            textRes = R.string.set_rep_based,
            iconId = R.drawable.ic_repeat,
            isSelected = workoutSetting.workoutSettingOption == WorkoutSettingOption.SET_REP_BASED,
            showCheckBox = true,
            onClick = {
                workoutSetting = workoutSetting.copy(workoutSettingOption = WorkoutSettingOption.SET_REP_BASED)
                WorkoutSettingPrefs.saveWorkoutOption(context, workoutSetting)
            },
            content = {
                SettingsWorkoutExpanded(
                    textRes = R.string.now_many_repetitions,
                    startNumber = repBasedLength,
                    endText = R.plurals.rep,
                    onClick = {
                        Toast.makeText(context, "Workout saved", Toast.LENGTH_LONG).show()
                        WorkoutSettingPrefs.saveRepBasedWorkout(context, it)
                    }
                )
            }
        )
    }
}

@Composable
private fun SettingsWorkoutExpanded(@StringRes textRes: Int, startNumber: Int, endText: Int, onClick: (Int) -> Unit) {
    val number = remember { mutableIntStateOf(startNumber) }

    Column {
        BodyBlackText(textRes = textRes)
        Spacer(modifier = Modifier.height(16.dp))
        NumberPicker(number = number, text = "${number.intValue} ${pluralStringResource(id = endText, count = number.intValue)}")
        Spacer(modifier = Modifier.height(8.dp))
        PrimaryButton(onClick = { onClick(number.intValue) }, textRes = R.string.save)
    }
}