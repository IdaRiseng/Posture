package no.sporty.posture.activities.nextMovement

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import no.sporty.posture.R
import no.sporty.posture.model.CustomExercise
import no.sporty.posture.model.WorkoutSettingOption
import no.sporty.posture.sharedPreferences.WorkoutSettingPrefs
import no.sporty.posture.ui.theme.PostureTheme
import no.sporty.posture.ui.theme.button.PrimaryButton
import no.sporty.posture.ui.theme.sharedElements.PostureWave
import no.sporty.posture.ui.theme.text.BodyBlackText
import no.sporty.posture.ui.theme.text.HeadlineBlackText


@Composable
fun NextMovement(
    continousMovement: MutableState<Boolean>,
    step: MutableState<Int>,
    exercise: CustomExercise,
    onStartNextMovementClick: () -> Unit
) {
    val movement = exercise.movements[step.value]
    val context = LocalContext.current
    val workoutSetting = WorkoutSettingPrefs.getWorkoutOption(context)
    PostureTheme {
        Column(
            Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box {
                Image(
                    painter = painterResource(id = movement.illustration),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                PostureWave(Modifier.align(Alignment.BottomCenter))
            }
            Column(Modifier.padding(16.dp)) {
                BodyBlackText(text = exercise.title)
                HeadlineBlackText(textRes = movement.title)
                PrimaryButton(onClick = onStartNextMovementClick, textRes = R.string.start_exercise, Modifier.padding(vertical = 16.dp))

                if (workoutSetting.workoutSettingOption == WorkoutSettingOption.TIME_BASED) {
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            colors = CheckboxDefaults.colors(checkmarkColor = Color.White),
                            modifier = Modifier.padding(0.dp),
                            checked = continousMovement.value,
                            onCheckedChange = { continousMovement.value = it }
                        )
                        BodyBlackText(textRes = R.string.continuous_exercise)
                    }
                }
            }
        }
    }
}
