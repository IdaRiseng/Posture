package no.sporty.posture.ui.theme.cards

import GreyCard
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import no.sporty.posture.R
import no.sporty.posture.extensions.shake
import no.sporty.posture.model.CustomExercise
import no.sporty.posture.model.Exercise
import no.sporty.posture.model.ShakeConfig
import no.sporty.posture.model.ShakeController
import no.sporty.posture.ui.theme.text.BodyBlackText
import no.sporty.posture.ui.theme.text.HeadlineBlackText
import no.sporty.posture.ui.theme.text.TitleBlackText
import kotlin.random.Random

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
    onCustomExerciseDeleteClicked: (CustomExercise) -> Unit,
    onClick: () -> Unit,
    shakeController: ShakeController? = null,
    showDismissButton: Boolean = false,
) {
    var showDismissDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .combinedClickable(
                onClick = {
                    onClick()
                },
                onLongClick = {
                    shakeController?.shake(ShakeConfig(true))
                },
            )
            .shake(shakeController)
    ) {
        GreyCard(
            padding = PaddingValues(),
            onDismiss = if (showDismissButton || shakeController?.shakeConfig?.isShaking == true) {
                { showDismissDialog = true }
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
    if (showDismissDialog) {
        DeleteCustomExerciseDialog(
            customExercise = customExercise,
            onCustomExerciseDeleteClicked = onCustomExerciseDeleteClicked,
            onDismiss = { showDismissDialog = false }
        )
    }
}

@Composable
fun DeleteCustomExerciseDialog(
    customExercise: CustomExercise,
    onCustomExerciseDeleteClicked: (CustomExercise) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            TitleBlackText(textRes = R.string.add)
            BodyBlackText(text = "Do you really want to delete this")
            FilledTonalButton(
                onClick = {
                    onCustomExerciseDeleteClicked(customExercise)
                    onDismiss()
                }) {
                BodyBlackText(text = stringResource(id = R.string.save))
            }
        }
    }
}
