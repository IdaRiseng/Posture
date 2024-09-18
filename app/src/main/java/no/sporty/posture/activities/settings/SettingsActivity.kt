package no.sporty.posture.activities.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import no.sporty.posture.R
import no.sporty.posture.activities.BaseActivity
import no.sporty.posture.activities.addOrRemoveCustomExercise.AddOrRemoveCustomExerciseActivity
import no.sporty.posture.sharedPreferences.ThemePrefs

class SettingsActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, SettingsActivity::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var needsToRecreateView by rememberSaveable { mutableStateOf(false) }
            Settings(
                onBackPressed = { onBackPressed(needsToRecreateView) },
                onCustomExerciseClicked = { startActivity(AddOrRemoveCustomExerciseActivity.newIntent(this)) },
                onThemeSelected = {
                    ThemePrefs.writeSelectedTheme(this, it)
                    AppCompatDelegate.setDefaultNightMode(it)
                    needsToRecreateView = true
                    recreate()
                },
                onSendEmail = { sendEmail(it) },
                onAffirmationClicked = { needsToRecreateView = true }
            )
        }
    }

    override fun onBackPressed() {
        onBackPressed(true)
        super.onBackPressed()
    }

    fun onBackPressed(needsToRecreateView: Boolean) {
        if (needsToRecreateView) {
            setResult(RESULT_OK)
        }
        finish()
    }

    private fun sendEmail(isProblem: Boolean) {
        val selectorIntent = Intent(Intent.ACTION_SENDTO)
        selectorIntent.data = Uri.parse("mailto:")

        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_EMAIL, arrayOf("ida@honningbier.no"))
            putExtra(Intent.EXTRA_SUBJECT, if (isProblem) getString(R.string.app_trouble) else getString(R.string.app_idea))
            putExtra(
                Intent.EXTRA_TEXT, """
                
                
                ––––––––––––––––––
                Device name: ${android.os.Build.MODEL}
                OS version: ${android.os.Build.VERSION.RELEASE}""".trimIndent()
            )
        }
        emailIntent.selector = selectorIntent
        startActivity(Intent.createChooser(emailIntent, "Send email..."))
    }
}