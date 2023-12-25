package no.sporty.posture.activities.nextMovement

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.OutlinedButton
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import no.sporty.posture.R
import no.sporty.posture.model.CustomExercise
import no.sporty.posture.model.Movement
import no.sporty.posture.model.WorkoutSettingOption
import no.sporty.posture.sharedPreferences.WorkoutSettingPrefs
import no.sporty.posture.ui.theme.PostureTheme
import no.sporty.posture.ui.theme.button.PrimaryButton
import no.sporty.posture.ui.theme.button.SecondaryButton
import no.sporty.posture.ui.theme.sharedElements.PostureWave
import no.sporty.posture.ui.theme.text.BodyBlackText
import no.sporty.posture.ui.theme.text.HeadlineBlackText
import no.sporty.posture.ui.theme.text.TitleBlackText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NextMovement(
    continousMovement: MutableState<Boolean>,
    exercise: CustomExercise,
    movement: Movement,
    onStartNextMovementClick: () -> Unit,
    onSkipMovementClick: () -> Unit,
) {
    val context = LocalContext.current
    val workoutSetting = WorkoutSettingPrefs.getWorkoutOption(context)
    val openBottomSheet = rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutine = rememberCoroutineScope()

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
            Column(
                Modifier
                    .padding(16.dp)
                    .weight(1f), verticalArrangement = Arrangement.SpaceBetween
            ) {
                if (movement.info != null) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        IconButton(onClick = {
                            coroutine.launch {
                                openBottomSheet.value = true
                                bottomSheetState.expand()
                            }
                        }) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(id = R.drawable.ic_info),
                                contentDescription = stringResource(id = R.string.info)
                            )
                        }
                    }
                }
                Column(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
                ) {
                    BodyBlackText(text = exercise.title)
                    HeadlineBlackText(textRes = movement.title)
                }
                Column {
                    if (workoutSetting.workoutSettingOption == WorkoutSettingOption.TIME_BASED) {
                        ContinousExerciseCheckbox(continousMovement)
                    }
                    PrimaryButton(onClick = onStartNextMovementClick, textRes = R.string.start_exercise)
                    SecondaryButton(onClick = onSkipMovementClick, textRes = R.string.skip)
                }
            }
        }
    }
    InfoBottomSheet(movement, openBottomSheet, bottomSheetState)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InfoBottomSheet(movement: Movement, openBottomSheet: MutableState<Boolean>, bottomSheetState: SheetState) {
    if (openBottomSheet.value && movement.info != null) {
        ModalBottomSheet(sheetState = bottomSheetState, onDismissRequest = { openBottomSheet.value = false }) {
            Column(
                Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 58.dp)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    painter = painterResource(id = movement.info.illustration),
                    contentDescription = "illustration",
                    contentScale = ContentScale.FillWidth
                )
                Spacer(modifier = Modifier.height(16.dp))
                TitleBlackText(textRes = R.string.info)
                BodyBlackText(textRes = movement.info.desc)
            }
        }
    }
}


@Composable
private fun ContinousExerciseCheckbox(continousMovement: MutableState<Boolean>) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(colors = CheckboxDefaults.colors(checkmarkColor = Color.White),
            modifier = Modifier.padding(0.dp),
            checked = continousMovement.value,
            onCheckedChange = { continousMovement.value = it })
        BodyBlackText(textRes = R.string.continuous_exercise)
    }
}