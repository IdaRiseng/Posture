package no.sporty.posture.activities.customExerciseDesc

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import no.sporty.posture.model.CustomExercise
import no.sporty.posture.model.Movement
import no.sporty.posture.model.SelectedMovements
import no.sporty.posture.sharedPreferences.CustomExercisePrefs
import java.lang.IllegalStateException

class CustomExerciseDescActivity : ComponentActivity() {

    companion object {
        fun newIntent(context: Context, movements: SelectedMovements, customExercise: CustomExercise?) =
            Intent(context, CustomExerciseDescActivity::class.java).apply {
                putExtra(SelectedMovements.TAG, movements)
                putExtra(CustomExercise.TAG, customExercise)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val customExercise: CustomExercise? = intent.getParcelableExtra(CustomExercise.TAG)
        val movements: SelectedMovements =
            intent.getParcelableExtra(SelectedMovements.TAG) ?: throw IllegalStateException("CustomExerciseDescActivity Should contain selectedmovements")

        setContent {
            CustomExerciseDesc(
                onBackPressed = { onBackPressedDispatcher.onBackPressed() },
                movements = movements.movements,
                customExercise = customExercise,
                onSaveClicked = { newCustomExercise ->
                    if (customExercise != null) {
                        CustomExercisePrefs.editCustomExercise(this, customExercise, newCustomExercise)
                        Toast.makeText(this, "Custom exercises edited", Toast.LENGTH_LONG).show()
                    } else {
                        CustomExercisePrefs.saveCustomExerciseList(this, newCustomExercise)
                        Toast.makeText(this, "Custom exercises added", Toast.LENGTH_LONG).show()
                    }
                    setResult(RESULT_OK)
                    finish()
                }
            )
        }
    }
}