package no.sporty.posture.ui.theme.cards

import GreyCard
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import no.sporty.posture.extensions.shake
import no.sporty.posture.model.CustomExercise
import no.sporty.posture.model.Exercise
import no.sporty.posture.model.ShakeConfig
import no.sporty.posture.model.ShakeController
import no.sporty.posture.model.rememberShakeController
import no.sporty.posture.ui.theme.text.BodyBlackText
import no.sporty.posture.ui.theme.text.HeadlineBlackText

@Composable
fun ExerciseCard(exercise: Exercise, onClick: (Exercise) -> Unit) {
    GreyCard(onClick = { onClick(exercise) }) {
        Column {
            Image(
                painter = painterResource(id = exercise.illustration),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                contentScale = ContentScale.FillWidth
            )
            Column(Modifier.padding(16.dp)) {
                HeadlineBlackText(text = exercise.title)
                Spacer(modifier = Modifier.height(8.dp))
                BodyBlackText(text = exercise.shortDesc)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomExerciseCard(
    customExercise: CustomExercise,
    onClick: (CustomExercise) -> Unit,
    shakeController: ShakeController? = null,
    showDismissButton: Boolean = false,
) {
    Column(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .combinedClickable(
                onClick = {
                    onClick(customExercise)
                },
                onLongClick = {
                    shakeController?.shake(ShakeConfig(true, translateX = 5f))
                },
            )
            .shake(shakeController)
    ) {
        GreyCard(
            padding = PaddingValues(),
            onDismiss = if (showDismissButton || shakeController?.shakeConfig?.isShaking == true) {
                { /* TODO */ }
            } else {
                null
            }
        ) {
            Column {
                Image(
                    painter = painterResource(id = customExercise.illustration),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp),
                    contentScale = ContentScale.FillWidth
                )
                Column(Modifier.padding(16.dp)) {
                    HeadlineBlackText(text = customExercise.title)
                    Spacer(modifier = Modifier.height(8.dp))
                    if (customExercise.desc.isNotBlank()) BodyBlackText(text = customExercise.desc)
                }
            }
        }
    }
}
