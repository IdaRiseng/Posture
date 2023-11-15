package no.sporty.posture.activities.customExercise

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import no.sporty.posture.R
import no.sporty.posture.model.Movement
import no.sporty.posture.ui.theme.button.PrimaryButton
import no.sporty.posture.ui.theme.cards.ButtonInfo
import no.sporty.posture.ui.theme.cards.base.SelectableCard
import no.sporty.posture.ui.theme.scaffold.PostureTopBarScaffold
import no.sporty.posture.ui.theme.text.BodyBlackText
import no.sporty.posture.ui.theme.text.HeadlineBlackText

@Composable
fun CreateCustomExercise(
    onBackPressed: () -> Unit,
    onContinueClicked: (List<Movement>) -> Unit
) {
    val addedMovements = remember { mutableStateListOf<Movement>() }
    PostureTopBarScaffold(onBackPressed, title = stringResource(id = R.string.custom_exercise)) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Movement.values().forEach { movement ->
                    ClickableCards(
                        title = movement.title,
                        desc = movement.title,
                        image = movement.illustration,
                        isSelected = addedMovements.contains(movement),
                        onSelected = {
                            if (addedMovements.contains(movement)) {
                                addedMovements.remove(movement)
                            } else {
                                addedMovements.add(movement)
                            }
                        }
                    )
                }
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(top = 16.dp)
            ) {
                if (addedMovements.isEmpty()) {
                    BodyBlackText(textRes = R.string.add_movements, textAlign = TextAlign.Center)
                } else {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                    ) {
                        addedMovements.forEach {
                            Image(
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(8.dp),
                                painter = painterResource(id = it.illustration),
                                contentDescription = it.name
                            )
                        }
                    }
                    PrimaryButton(onClick = { onContinueClicked(addedMovements) }, textRes = R.string._continue)
                }
            }
        }
    }
}

@Composable
private fun ClickableCards(
    @StringRes title: Int,
    @StringRes desc: Int,
    buttonInfo: ButtonInfo? = null,
    @DrawableRes image: Int? = null,
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    val localDensity = LocalDensity.current
    var columnHeightDp by remember { mutableStateOf(0.dp) }

    SelectableCard(isSelected = isSelected, onSelected = onSelected) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    columnHeightDp = with(localDensity) { coordinates.size.height.toDp() }
                },
        ) {
            Column(
                Modifier
                    .padding(16.dp)
                    .weight(3f)
            ) {
                HeadlineBlackText(text = stringResource(id = title))
                Spacer(modifier = Modifier.height(8.dp))
                BodyBlackText(text = stringResource(id = desc))
                buttonInfo?.let {
                    Spacer(modifier = Modifier.height(16.dp))
                    PrimaryButton(onClick = it.onClick, textRes = it.text)
                }
            }

            image?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = Modifier
                        .height(columnHeightDp)
                        .fillMaxWidth()
                        .weight(1.5f), // TODO gotta do something about this
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}
