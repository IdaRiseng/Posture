package no.sporty.posture.activities.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import no.sporty.posture.activities.addOrRemoveCustomExercise.AddOrRemoveCustomExerciseActivity

class SettingsActivity : ComponentActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, SettingsActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Settings(
                onBackPressed = { onBackPressedDispatcher.onBackPressed() },
                onCustomExerciseClicked = { startActivity(AddOrRemoveCustomExerciseActivity.newIntent(this)) }
            )
        }
    }
}