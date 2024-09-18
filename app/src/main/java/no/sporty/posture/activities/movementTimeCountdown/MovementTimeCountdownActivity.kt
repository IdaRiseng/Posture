package no.sporty.posture.activities.movementTimeCountdown

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import no.sporty.posture.activities.BaseActivity
import no.sporty.posture.model.Movement
import no.sporty.posture.model.PostureVibrator

class MovementTimeCountdownActivity : BaseActivity() {

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
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        setContent {
            MovementTimeCountdown(
                movement = movement,
                showAlert = showAlertDialog,
                onExit = {
                    setResult(RESULT_CANCELED)
                    finish()
                },
                onFinish = {
                    PostureVibrator.vibrate(this)
                    Handler().postDelayed({
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                        setResult(RESULT_CONTINUE)
                        finish()
                    }, 3_000)
                }
            )
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        showAlertDialog.value = true
    }
}