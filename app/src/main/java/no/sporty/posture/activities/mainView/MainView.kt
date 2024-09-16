package no.sporty.posture.activities.mainView

import SmallDisabledBlackText
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import no.sporty.posture.R
import no.sporty.posture.activities.mainView.elements.MainTopBar
import no.sporty.posture.extensions.overScrollColor
import no.sporty.posture.model.CustomExercise
import no.sporty.posture.model.Exercise
import no.sporty.posture.model.ShakeConfig
import no.sporty.posture.model.ShakeController
import no.sporty.posture.model.TopBarInfo
import no.sporty.posture.model.rememberShakeController
import no.sporty.posture.sharedPreferences.InfoCardPrefs
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
    onCustomExerciseDeleteClicked: (CustomExercise) -> Unit,
    onSettingsClicked: () -> Unit,
    onExerciseClicked: (Exercise) -> Unit,
    onCreateCustomExerciseClicked: () -> Unit,
    onCustomExerciseClicked: (CustomExercise) -> Unit,
    affirmation: String?
) {
    val scrollState = rememberScrollState()
    val shakeController = rememberShakeController()

    PostureTheme {
        Column(
            Modifier
                .overScrollColor(scrollState, MaterialTheme.colorScheme.primary)
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        shakeController.shake(ShakeConfig(isShaking = false))
                    })
                }
        ) {
            MainTopBar(topBarInfo, onSettingsClicked, affirmation)
            Column(Modifier.padding(16.dp)) {
                Info(onCreateCustomExerciseClicked)
                CustomExercises(
                    customExercises = customExercises,
                    onCustomExerciseClicked = onCustomExerciseClicked,
                    onCustomExerciseDeleteClicked = onCustomExerciseDeleteClicked,
                    shakeController = shakeController
                )
                Exercises(exercises, onExerciseClicked)

                Column(
                    modifier = Modifier
                        .padding(top = 32.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SmallDisabledBlackText(text = stringResource(id = R.string.remember_quote))
                }
            }
        }
    }
}


@Composable
private fun Info(onButtonClicked: () -> Unit) {
    val context = LocalContext.current
    var thankYouCardVisible by remember { mutableStateOf(!InfoCardPrefs.isThankYouCardDismissed(context)) }
    var customExerciseCardVisible by remember { mutableStateOf(!InfoCardPrefs.isCustomExerciseCarDismissed(context)) }

    AnimatedVisibility(visible = thankYouCardVisible || customExerciseCardVisible) {
        HeadlineBlackText(textRes = R.string.info, padding = PaddingValues(vertical = 16.dp))
    }

    AnimatedVisibility(visible = thankYouCardVisible, exit = fadeOut() + slideOutVertically()) {
        InfoCard(
            title = R.string.info_thank_you_title,
            desc = R.string.info_thank_you_desc,
            icon = R.drawable.ic_hand_heart,
            onDismiss = {
                InfoCardPrefs.thankYouCardDismissed(context)
                thankYouCardVisible = false
            }
        )
    }
    AnimatedVisibility(visible = customExerciseCardVisible, exit = fadeOut() + slideOutVertically()) {
        InfoCard(
            title = R.string.info_custom_exercise_title,
            desc = R.string.info_custom_exercise_desc,
            buttonInfo = ButtonInfo(onButtonClicked, R.string.create),
            image = R.drawable.front_image_idk,
            onDismiss = {
                InfoCardPrefs.customExerciseCardDismissed(context)
                customExerciseCardVisible = false
            }
        )
    }
}

@Composable
private fun Exercises(exercises: List<Exercise>, onExerciseClicked: (Exercise) -> Unit) {
    HeadlineBlackText(textRes = R.string.exercises, padding = PaddingValues(vertical = 16.dp))
    exercises.forEach {
        ExerciseCard(it, onExerciseClicked)
    }
}

@Composable
private fun CustomExercises(
    customExercises: List<CustomExercise>,
    onCustomExerciseClicked: (CustomExercise) -> Unit,
    onCustomExerciseDeleteClicked: (CustomExercise) -> Unit,
    shakeController: ShakeController
) {
    if (customExercises.isNotEmpty()) {
        HeadlineBlackText(textRes = R.string.custom_exercise, padding = PaddingValues(vertical = 16.dp))
        customExercises.forEach {
            CustomExerciseCard(
                customExercise = it,
                onClick = { onCustomExerciseClicked(it) },
                onCustomExerciseDeleteClicked = onCustomExerciseDeleteClicked,
                shakeController = shakeController
            )
        }
    }
}