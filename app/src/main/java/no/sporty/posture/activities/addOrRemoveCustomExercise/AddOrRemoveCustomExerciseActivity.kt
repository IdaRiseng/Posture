package no.sporty.posture.activities.addOrRemoveCustomExercise

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import no.sporty.posture.sharedPreferences.CustomExercisePrefs

class AddOrRemoveCustomExerciseActivity : ComponentActivity() {
    companion object {
        fun newIntent(context: Context) = Intent(context, AddOrRemoveCustomExerciseActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val customExercises = CustomExercisePrefs.getCustomExerciseList(this)

        setContent {
            AddOrRemoveCustomExercise(
                onBackPressed = { onBackPressedDispatcher.onBackPressed() },
                customExercises = customExercises
            )
        }
    }
}