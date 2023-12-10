package no.sporty.posture.activities.customExerciseDesc

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import no.sporty.posture.R
import no.sporty.posture.model.CustomExercise
import no.sporty.posture.model.Movement
import no.sporty.posture.ui.theme.button.PrimaryButton
import no.sporty.posture.ui.theme.inputField.InputField
import no.sporty.posture.ui.theme.scaffold.PostureTopBarScaffold
import no.sporty.posture.ui.theme.text.BodyBlackText

@Composable
fun CustomExerciseDesc(
    onBackPressed: () -> Unit,
    customExercise: CustomExercise?,
    movements: List<Movement>,
    onSaveClicked: (CustomExercise) -> Unit
) {
    val context = LocalContext.current
    var title by remember { mutableStateOf(customExercise?.title ?: "") }
    var desc by remember { mutableStateOf(customExercise?.desc ?: "") }
    var chosenImage by remember { mutableIntStateOf(customExercise?.illustration ?: movements.first().illustration) }

    PostureTopBarScaffold(onBackPressed, title = stringResource(id = R.string.custom_exercise)) {
        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(MaterialTheme.colorScheme.tertiary),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = chosenImage),
                    contentDescription = "picture"
                )
            }


            Column(Modifier.padding(16.dp)) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                ) {
                    movements.forEach {
                        Image(
                            modifier = Modifier
                                .size(80.dp)
                                .padding(8.dp)
                                .clickable { chosenImage = it.illustration },
                            painter = painterResource(id = it.illustration),
                            contentDescription = it.name
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                BodyBlackText(text = "Title")
                Spacer(modifier = Modifier.height(8.dp))
                InputField(value = title, onValueChange = { title = it })

                Spacer(modifier = Modifier.height(16.dp))
                BodyBlackText(text = "Description")
                Spacer(modifier = Modifier.height(8.dp))
                InputField(value = desc, onValueChange = { desc = it })

                Spacer(modifier = Modifier.height(16.dp))
                PrimaryButton(onClick = {
                    when {
                        chosenImage == null -> {
                            Toast.makeText(context, "Please select an image", Toast.LENGTH_LONG).show()
                        }

                        title.isBlank() -> {
                            Toast.makeText(context, "Please write a title", Toast.LENGTH_LONG).show()
                        }

                        else -> {
                            val customExercise = CustomExercise(
                                title = title,
                                desc = desc,
                                illustration = chosenImage!!,
                                movements = movements
                            )
                            onSaveClicked(customExercise)
                        }
                    }

                }, textRes = R.string.save)
            }
        }
    }
}
