package no.sporty.posture.activities.nextMovement

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import no.sporty.posture.activities.movementRepCountdown.MovementRepCountdownActivity
import no.sporty.posture.activities.movementTimeCountdown.MovementTimeCountdownActivity
import no.sporty.posture.model.CustomExercise
import no.sporty.posture.model.WorkoutSettingOption
import no.sporty.posture.sharedPreferences.WorkoutSettingPrefs

class NextMovementActivity : ComponentActivity() {

    companion object {
        fun newIntent(context: Context, exercise: CustomExercise) = Intent(context, NextMovementActivity::class.java).apply {
            putExtra(CustomExercise.TAG, exercise)
        }
    }

    private val movementStep: MutableState<Int> = mutableStateOf(0)
    private val continousMovement: MutableState<Boolean> = mutableStateOf(false)
    private lateinit var exercise: CustomExercise


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        exercise = intent.getParcelableExtra(CustomExercise.TAG) ?: throw IllegalStateException("Should contain CustomExercise")

        setContent {
            NextMovement(
                continousMovement = continousMovement,
                exercise = exercise,
                step = movementStep,
                onStartNextMovementClick = {
                    startMovementActivity()
                }
            )
        }
    }

    private fun startMovementActivity() {
        val workoutSetting = WorkoutSettingPrefs.getWorkoutOption(this)
        if (workoutSetting.workoutSettingOption == WorkoutSettingOption.TIME_BASED) {
            startMovementResult.launch(MovementTimeCountdownActivity.newIntent(this, exercise.movements[movementStep.value]))
        } else {
            startMovementResult.launch(MovementRepCountdownActivity.newIntent(this, exercise.movements[movementStep.value]))
        }
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    private var startMovementResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        when {
            result.resultCode == RESULT_CANCELED -> {
                setResult(RESULT_CANCELED)
                finish()
            }

            movementStep.value < exercise.movements.size - 1 -> {
                movementStep.value = movementStep.value + 1
                if (continousMovement.value) {
                    startMovementActivity()
                }
            }

            movementStep.value == exercise.movements.size - 1 -> {
                setResult(RESULT_OK)
                finish()
            }
        }
    }
}