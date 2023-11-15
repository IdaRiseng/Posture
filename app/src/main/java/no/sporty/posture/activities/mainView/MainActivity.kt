package no.sporty.posture.activities.mainView

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import no.sporty.posture.activities.customExercise.CustomExerciseActivity
import no.sporty.posture.activities.setMovementCount.SetMovementCountActivity
import no.sporty.posture.activities.settings.SettingsActivity
import no.sporty.posture.model.Exercise
import no.sporty.posture.model.TopBarInfo
import no.sporty.posture.sharedPreferences.StreakPrefs
import java.time.LocalDate

class MainActivity : ComponentActivity() {

    private val topBarInfo: MutableState<TopBarInfo> = mutableStateOf(TopBarInfo())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val exercises = Exercise.values().asList() // hardcoded exercises
        StreakPrefs.checkStreakLogin(this)

        setContent {
            MainView(
                topBarInfo = topBarInfo.value,
                exercises = exercises,
                onCustomExerciseClicked = { startActivity(CustomExerciseActivity.newIntent(this)) },
                onSettingsClicked = { startActivity(SettingsActivity.newIntent(this)) },
                onExerciseClicked = { startExerciseResult.launch(SetMovementCountActivity.newIntent(this, it)) }
            )
        }
    }

    override fun onResume() {
        super.onResume()
        updateTopBar()
    }

    private var startExerciseResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            StreakPrefs.saveDate(this, LocalDate.now())
            updateTopBar()
        }
    }

    private fun updateTopBar() {
        topBarInfo.value = topBarInfo.value.copy(
            streak = StreakPrefs.getStreak(this),
            total = StreakPrefs.getTotal(this),
            datesExercised = StreakPrefs.getDateList(this)
        )
    }
}


