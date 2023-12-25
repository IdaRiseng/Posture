package no.sporty.posture.activities.setMovementCount


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.res.stringResource
import no.sporty.posture.activities.nextMovement.NextMovementActivity
import no.sporty.posture.model.CustomExercise
import no.sporty.posture.model.Exercise
import no.sporty.posture.model.WorkoutSettingOption
import no.sporty.posture.sharedPreferences.WorkoutSettingPrefs
import no.sporty.posture.sharedPreferences.WorkoutSettingPrefs.getTimeBasedWorkout

class SetMovementCountActivity : ComponentActivity() {

    companion object {
        const val EXERCISE_TAG = "exercise_tag"
        fun newIntent(context: Context, exercise: Exercise) = Intent(context, SetMovementCountActivity::class.java).apply {
            putExtra(EXERCISE_TAG, exercise)
        }
    }

    private lateinit var exercise: Exercise

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        exercise = intent.getSerializableExtra(EXERCISE_TAG) as Exercise
        val workoutSetting = WorkoutSettingPrefs.getWorkoutOption(this)

        setContent {
            SetMovementCount(
                exercise = exercise,
                workoutSetting = workoutSetting,
                onContinueRepClicked = { exerciseLength ->
                    val customExercise = CustomExercise(
                        title = getString(exercise.title),
                        desc = getString(exercise.shortDesc),
                        movements = exercise.movements,
                        illustration = exercise.illustration
                    )
                    startMovementResult.launch(NextMovementActivity.newIntent(this, customExercise, exerciseLength.length))
                },
                onContinueTimeClicked = { exerciseLength ->
                    val standardExerciseLength = exerciseLength.length / getTimeBasedWorkout(this)
                    val customExercise = CustomExercise(
                        title = getString(exercise.title),
                        desc = getString(exercise.shortDesc),
                        movements = exercise.movements,
                        illustration = exercise.illustration
                    )
                    startMovementResult.launch(NextMovementActivity.newIntent(this, customExercise, standardExerciseLength.toInt()))
                }
            )
        }
    }

    private var startMovementResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        when (result.resultCode) {
            RESULT_CANCELED -> {
                setResult(RESULT_CANCELED)
                finish()
            }

            RESULT_OK -> {
                setResult(RESULT_OK)
                finish()
            }
        }
    }
}