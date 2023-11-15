package no.sporty.posture.activities.customExercise


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import no.sporty.posture.activities.customExerciseDesc.CustomExerciseDescActivity
import no.sporty.posture.model.SelectedMovements
import no.sporty.posture.sharedPreferences.StreakPrefs
import java.time.LocalDate

class CustomExerciseActivity : ComponentActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, CustomExerciseActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CustomExercise(
                onBackPressed = { onBackPressed() },
                onContinueClicked = { movements ->
                    val selectedMovements = SelectedMovements(movements)
                    startCustomExerciseResult.launch(CustomExerciseDescActivity.newIntent(this, selectedMovements))
                }
            )
        }
    }

    private var startCustomExerciseResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            finish()
        }
    }
}