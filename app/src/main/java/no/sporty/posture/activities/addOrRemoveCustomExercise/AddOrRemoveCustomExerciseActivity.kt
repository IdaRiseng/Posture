package no.sporty.posture.activities.addOrRemoveCustomExercise

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import no.sporty.posture.activities.BaseActivity
import no.sporty.posture.activities.customExercise.CreateCustomExerciseActivity
import no.sporty.posture.model.CustomExercise
import no.sporty.posture.sharedPreferences.CustomExercisePrefs

class AddOrRemoveCustomExerciseActivity : BaseActivity() {
    companion object {
        fun newIntent(context: Context) = Intent(context, AddOrRemoveCustomExerciseActivity::class.java)
    }

    private val customExercises: MutableState<List<CustomExercise>> = mutableStateOf(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AddOrRemoveCustomExercise(
                addCustomExerciseClicked = { startActivity(CreateCustomExerciseActivity.newIntent(this)) },
                editCustomExerciseClicked = { startActivity(CreateCustomExerciseActivity.newIntent(this, it)) },
                onBackPressed = { onBackPressedDispatcher.onBackPressed() },
                onCustomDeleteClicked = {
                    CustomExercisePrefs.removeCustomExercise(this, it)
                    customExercises.value = CustomExercisePrefs.getCustomExerciseList(this)
                },
                customExercises = customExercises.value
            )
        }
    }

    override fun onResume() {
        super.onResume()
        customExercises.value = CustomExercisePrefs.getCustomExerciseList(this)
    }
}