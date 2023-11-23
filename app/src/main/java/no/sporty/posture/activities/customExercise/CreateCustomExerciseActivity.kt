package no.sporty.posture.activities.customExercise


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import no.sporty.posture.activities.customExerciseDesc.CustomExerciseDescActivity
import no.sporty.posture.model.CustomExercise
import no.sporty.posture.model.SelectedMovements

class CreateCustomExerciseActivity : ComponentActivity() {

    companion object {
        fun newIntent(context: Context, customExercise: CustomExercise? = null) = Intent(context, CreateCustomExerciseActivity::class.java).apply {
            putExtra(CustomExercise.TAG, customExercise)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val customExercise: CustomExercise? = intent.getParcelableExtra(CustomExercise.TAG)


        setContent {
            CreateCustomExercise(
                onBackPressed = { onBackPressed() },
                customExercise = customExercise,
                onContinueClicked = { movements ->
                    val selectedMovements = SelectedMovements(movements)
                    startCustomExerciseResult.launch(CustomExerciseDescActivity.newIntent(this, selectedMovements, customExercise))
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