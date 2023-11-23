package no.sporty.posture.activities.movementTimeCountdown

import BigHeadlineBlackText
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import no.sporty.posture.R
import no.sporty.posture.model.Movement
import no.sporty.posture.sharedPreferences.WorkoutSettingPrefs
import no.sporty.posture.ui.theme.PostureTheme
import no.sporty.posture.ui.theme.sharedElements.AlertDialog
import no.sporty.posture.ui.theme.sharedElements.PostureWave
import no.sporty.posture.ui.theme.text.HeadlineAlwaysWhiteText
import no.sporty.posture.ui.theme.text.HeadlineBlackText

@Composable
fun MovementTimeCountdown(
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
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            Image(
                painter = painterResource(id = movement.video),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            PostureWave(Modifier.align(Alignment.BottomCenter))
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            HeadlineBlackText(textRes = movement.title, padding = PaddingValues(16.dp))
            CountDown(WorkoutSettingPrefs.getTimeBasedWorkout(context) * 60L) {
                onFinish()
            }
        }
    }
}

@Composable
private fun CountDown(
    durability: Long,
    onFinish: () -> Unit
) {
    val timeLeftMs by rememberCountDownTimerState(durability)
    Box(Modifier.size(180.dp)) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            strokeCap  = StrokeCap.Round,
            progress = timeLeftMs.toFloat() / durability.toFloat()
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (timeLeftMs > 0) {
                BigHeadlineBlackText(timeLeftMs.toString())
            }
            AnimatedVisibility(visible = timeLeftMs <= 0, enter = scaleIn()) {
                HeadlineBlackText(stringArrayResource(id = R.array.positive_words).random())
                onFinish()
            }
        }
    }
}

@Composable
private fun rememberCountDownTimerState(initialMillis: Long): MutableState<Long> {
    val step: Long = 1 //countdown seconds
    val timeLeft = remember { mutableLongStateOf(initialMillis) }
    LaunchedEffect(initialMillis, step) {
        while (isActive && timeLeft.longValue > 0) {
            timeLeft.longValue = (timeLeft.longValue - step).coerceAtLeast(0)
            delay(step * 1000)
        }
    }
    return timeLeft
}
