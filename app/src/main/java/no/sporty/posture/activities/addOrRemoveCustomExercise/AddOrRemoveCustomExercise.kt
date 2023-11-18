package no.sporty.posture.activities.addOrRemoveCustomExercise

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import no.sporty.posture.R
import no.sporty.posture.model.CustomExercise
import no.sporty.posture.ui.theme.cards.CustomExerciseCard
import no.sporty.posture.ui.theme.scaffold.PostureTopBarScaffold

@Composable
fun AddOrRemoveCustomExercise(
    onBackPressed: () -> Unit,
    customExercises: List<CustomExercise>
) {
    PostureTopBarScaffold(onBackPressed, title = stringResource(id = R.string.custom_exercise)) {
        Column(Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .height(150.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.tertiary),
                contentAlignment = Alignment.Center,

            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(id = R.string.add))
            }

            customExercises.forEach {
                CustomExerciseCard(
                    customExercise = it,
                    onClick = {},
                    showDismissButton = true
                )
            }
        }
    }
}