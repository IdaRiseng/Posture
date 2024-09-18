package no.sporty.posture.activities.nextMovement

import android.content.Context
import android.content.Intent
import android.health.connect.datatypes.units.Length
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import no.sporty.posture.activities.BaseActivity
import no.sporty.posture.activities.mainView.MainActivity.Companion.EXERCISE_NAME
import no.sporty.posture.activities.movementRepCountdown.MovementRepCountdownActivity
import no.sporty.posture.activities.movementTimeCountdown.MovementTimeCountdownActivity
import no.sporty.posture.model.CustomExercise
import no.sporty.posture.model.Movement
import no.sporty.posture.model.WorkoutSettingOption
import no.sporty.posture.sharedPreferences.WorkoutSettingPrefs

class NextMovementActivity : BaseActivity() {

    companion object {
        private const val EXERCISE_LENGTH = "exercise_length"
        fun newIntent(context: Context, exercise: CustomExercise, exerciseLength: Int) = Intent(context, NextMovementActivity::class.java).apply {
            putExtra(CustomExercise.TAG, exercise)
            putExtra(EXERCISE_LENGTH, exerciseLength)
        }
    }

    private val movementStep: MutableState<Int> = mutableIntStateOf(0)
    private val continousMovement: MutableState<Boolean> = mutableStateOf(false)
    private var exerciseLength: Int = 0
    private lateinit var exercise: CustomExercise
    private lateinit var movement: MutableState<Movement>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        exercise = intent.getParcelableExtra(CustomExercise.TAG) ?: throw IllegalStateException("Should contain CustomExercise")
        exerciseLength = intent.getIntExtra(EXERCISE_LENGTH, 0)
        movement = mutableStateOf(exercise.movements[movementStep.value])

        setContent {
            NextMovement(
                continousMovement = continousMovement,
                exercise = exercise,
                movement = movement.value,
                onStartNextMovementClick = {
                    startMovementActivity()
                },
                onSkipMovementClick = {
                    movement.value = exercise.movements.shuffled()[movementStep.value]
                }
            )
        }
    }

    private fun startMovementActivity() {
        val workoutSetting = WorkoutSettingPrefs.getWorkoutOption(this)
        if (workoutSetting.workoutSettingOption == WorkoutSettingOption.TIME_BASED) {
            startMovementResult.launch(MovementTimeCountdownActivity.newIntent(this, movement.value))
        } else {
            startMovementResult.launch(MovementRepCountdownActivity.newIntent(this, movement.value))
        }
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    private var startMovementResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        when {
            result.resultCode == RESULT_CANCELED -> {
                setResult(RESULT_CANCELED, intent)
                finish()
            }

            movementStep.value < exerciseLength - 1 -> {
                movementStep.value = movementStep.value + 1
                movement.value = exercise.movements[movementStep.value]
                if (continousMovement.value) {
                    startMovementActivity()
                }
            }

            movementStep.value == exerciseLength - 1 -> {
                val intent = Intent().apply {
                    putExtra(EXERCISE_NAME, exercise.title)
                }
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}