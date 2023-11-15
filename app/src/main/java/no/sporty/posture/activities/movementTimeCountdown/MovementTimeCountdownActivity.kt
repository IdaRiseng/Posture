package no.sporty.posture.activities.movementTimeCountdown

import android.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import no.sporty.posture.model.Movement

class MovementTimeCountdownActivity : ComponentActivity() {

    companion object {
        const val MOVEMENT_TAG = "movement_tag"
        const val RESULT_CONTINUE = 1234
        fun newIntent(context: Context, movement: Movement) = Intent(context, MovementTimeCountdownActivity::class.java).apply {
            putExtra(MOVEMENT_TAG, movement)
        }
    }

    private val showAlertDialog: MutableState<Boolean> = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movement = intent.getSerializableExtra(MOVEMENT_TAG) as Movement
        setContent {
            MovementTimeCountdown(
                movement = movement,
                showAlert = showAlertDialog,
                onExit = {
                    setResult(RESULT_CANCELED)
                    finish()
                },
                onFinish = {
                    Handler().postDelayed({
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                        setResult(RESULT_CONTINUE)
                        finish()
                    }, 3_000)
                }
            )
        }
    }

    override fun onBackPressed() {
        showAlertDialog.value = true
    }
}