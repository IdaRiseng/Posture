package no.sporty.posture.activities.customExerciseDesc

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import no.sporty.posture.model.Movement
import no.sporty.posture.model.SelectedMovements
import no.sporty.posture.sharedPreferences.CustomExercisePrefs
import java.lang.IllegalStateException

class CustomExerciseDescActivity : ComponentActivity() {

    companion object {
        fun newIntent(context: Context, movements: SelectedMovements) = Intent(context, CustomExerciseDescActivity::class.java).apply {
            putExtra(SelectedMovements.TAG, movements)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movements: SelectedMovements =
            intent.getParcelableExtra(SelectedMovements.TAG) ?: throw IllegalStateException("CustomExerciseDescActivity Should contain selectedmovements")

        setContent {
            CustomExerciseDesc(
                onBackPressed = { onBackPressedDispatcher.onBackPressed() },
                movements = movements.movements,
                onSaveClicked = { customExercise ->
                    CustomExercisePrefs.saveCustomExerciseList(this, customExercise)
                    Toast.makeText(this, "Custom exercises added", Toast.LENGTH_LONG).show()
                    setResult(RESULT_OK)
                    finish()
                }
            )
        }
    }
}