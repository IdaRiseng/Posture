package no.sporty.posture.activities.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import no.sporty.posture.activities.addOrRemoveCustomExercise.AddOrRemoveCustomExerciseActivity
import no.sporty.posture.sharedPreferences.ThemePrefs

class SettingsActivity : ComponentActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, SettingsActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var isLanguageChanged by rememberSaveable { mutableStateOf(false) }
            Settings(
                onBackPressed = { onBackpressed(isLanguageChanged) },
                onCustomExerciseClicked = { startActivity(AddOrRemoveCustomExerciseActivity.newIntent(this)) },
                onThemeSelected = {
                    ThemePrefs.writeSelectedTheme(this, it)
                    AppCompatDelegate.setDefaultNightMode(it)
                    recreate()
                    isLanguageChanged = true
                }
            )
        }
    }

    private fun onBackpressed(isLanguageChanged: Boolean){
        if(isLanguageChanged){
            setResult(RESULT_OK)
        }
        finish()
    }
}