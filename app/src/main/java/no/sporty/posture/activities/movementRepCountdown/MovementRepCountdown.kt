package no.sporty.posture.activities.movementRepCountdown

import BigHeadlineBlackText
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import no.sporty.posture.R
import no.sporty.posture.extensions.formatZeros
import no.sporty.posture.model.Movement
import no.sporty.posture.sharedPreferences.WorkoutSettingPrefs
import no.sporty.posture.ui.theme.PostureTheme
import no.sporty.posture.ui.theme.button.PrimaryButton
import no.sporty.posture.ui.theme.sharedElements.AlertDialog
import no.sporty.posture.ui.theme.sharedElements.PostureWave
import no.sporty.posture.ui.theme.sharedElements.VideoPlayer
import no.sporty.posture.ui.theme.text.BodyBlackText
import no.sporty.posture.ui.theme.text.HeadlineAlwaysWhiteText
import no.sporty.posture.ui.theme.text.HeadlineBlackText

@Composable
fun MovementRepCountdown(
    movement: Movement,
    showAlert: MutableState<Boolean>,
    onExit: () -> Unit,
    onFinish: () -> Unit
) {
    PostureTheme {
        val showTitle = remember { mutableStateOf(true) }
        AnimatedVisibility(visible = showTitle.value, enter = fadeIn(), exit = fadeOut()) {
            ShowMovementTitle(showTitle, movement)
        }
        AnimatedVisibility(visible = !showTitle.value, enter = fadeIn(), exit = fadeOut()) {
            CountDownView(movement = movement, onFinish = onFinish)
        }
        AlertDialog(showAlert, onExit)
    }
}

@Composable
private fun ShowMovementTitle(showTitle: MutableState<Boolean>, movement: Movement) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        HeadlineAlwaysWhiteText(textRes = movement.title)
    }
    LaunchedEffect(showTitle) {
        delay(3000)
        showTitle.value = false
    }
}


@Composable
private fun CountDownView(
    movement: Movement,
    onFinish: () -> Unit
) {
    val context = LocalContext.current
    val positiveWord by rememberSaveable { mutableStateOf(context.resources.getStringArray(R.array.positive_words).random()) }
    var countDown by rememberSaveable { mutableFloatStateOf(WorkoutSettingPrefs.getRepBasedWorkout(context)) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box {
            VideoPlayer(res = movement.video)
            PostureWave(Modifier.align(Alignment.BottomCenter))
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            HeadlineBlackText(textRes = movement.title, padding = PaddingValues(16.dp))
            BodyBlackText(textRes = R.string.repetitions_left)
            Spacer(modifier = Modifier.height(32.dp))

            if (countDown != 0f) BigHeadlineBlackText(text = countDown.formatZeros())
            AnimatedVisibility(visible = countDown == 0f, enter = scaleIn()) {
                BigHeadlineBlackText(positiveWord)
                onFinish()
            }

            Spacer(modifier = Modifier.height(32.dp))
            PrimaryButton(onClick = { countDown -= 1 }, textRes = R.string.done)

        }
    }
}

