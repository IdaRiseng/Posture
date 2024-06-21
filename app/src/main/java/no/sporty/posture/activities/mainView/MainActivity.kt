package no.sporty.posture.activities.mainView

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import com.google.android.exoplayer2.BuildConfig
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.coroutines.launch
import no.sporty.posture.activities.customExercise.CreateCustomExerciseActivity
import no.sporty.posture.activities.nextMovement.NextMovementActivity
import no.sporty.posture.activities.setMovementCount.SetMovementCountActivity
import no.sporty.posture.activities.settings.SettingsActivity
import no.sporty.posture.model.CustomExercise
import no.sporty.posture.model.Exercise
import no.sporty.posture.model.TopBarInfo
import no.sporty.posture.model.data.AffirmationApiService
import no.sporty.posture.model.data.AffirmationResponse
import no.sporty.posture.model.manager.AffirmationManager
import no.sporty.posture.sharedPreferences.AffirmationPref
import no.sporty.posture.sharedPreferences.CustomExercisePrefs
import no.sporty.posture.sharedPreferences.SavedExerciseInfo
import no.sporty.posture.sharedPreferences.StreakPrefs
import no.sporty.posture.sharedPreferences.ThemePrefs
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    companion object {
        const val EXERCISE_NAME = "exercise_name"
    }

    private val topBarInfo: MutableState<TopBarInfo> = mutableStateOf(TopBarInfo())
    private val customExercises: MutableState<List<CustomExercise>> = mutableStateOf(emptyList())
    private var mInterstitialAd: InterstitialAd? = null
    private var affirmations: MutableState<String?> = mutableStateOf(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this)
        ThemePrefs.readSelectedTheme(this)?.let { AppCompatDelegate.setDefaultNightMode(it) }

        val exercises = Exercise.values().asList() // hardcoded exercises
        customExercises.value = CustomExercisePrefs.getCustomExerciseList(this)

        StreakPrefs.checkStreakLogin(this)
        loadAd()
        setContent {
            val coroutine = rememberCoroutineScope()
            MainView(
                topBarInfo = topBarInfo.value,
                exercises = exercises,
                customExercises = customExercises.value,
                onCreateCustomExerciseClicked = { createCustomExerciseResult.launch(CreateCustomExerciseActivity.newIntent(this)) },
                onSettingsClicked = { settingsResult.launch(SettingsActivity.newIntent(this)) },
                onExerciseClicked = { startExerciseResult.launch(SetMovementCountActivity.newIntent(this, it)) },
                onCustomExerciseDeleteClicked = {
                    CustomExercisePrefs.removeCustomExercise(this, it)
                    customExercises.value = CustomExercisePrefs.getCustomExerciseList(this)
                },
                affirmation = affirmations.value,
                onCustomExerciseClicked = { startExerciseResult.launch(NextMovementActivity.newIntent(this, it, it.movements.size)) }
            )
            LaunchedEffect(this) {
                coroutine.launch {
                    getAffirmation()
                }
            }
        }
    }

    private var settingsResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            recreate()
        }
    }

    override fun onResume() {
        super.onResume()
        updateTopBar()
        customExercises.value = CustomExercisePrefs.getCustomExerciseList(this)
    }

    private var createCustomExerciseResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            customExercises.value = CustomExercisePrefs.getCustomExerciseList(this)
        }
    }

    private var startExerciseResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            val exerciseName = it.data?.getStringExtra(EXERCISE_NAME)
            StreakPrefs.saveDate(this, SavedExerciseInfo(exerciseName, LocalDate.now()))
            updateTopBar()
            mInterstitialAd?.show(this)
        }
    }

    private fun updateTopBar() {
        topBarInfo.value = topBarInfo.value.copy(
            streak = StreakPrefs.getStreak(this),
            total = StreakPrefs.getTotal(this),
            datesExercised = StreakPrefs.getDateList(this)
        )
    }

    private fun loadAd() {
        val adRequest = AdRequest.Builder().build()
        //admob
        //real prod version is this: ca-app-pub-2580430564049859/5931067653
        //test is: ca-app-pub-3940256099942544/1033173712
        val id = if (BuildConfig.DEBUG) "ca-app-pub-3940256099942544/1033173712" else "ca-app-pub-2580430564049859/5931067653"
        InterstitialAd.load(this, id, adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
            }
        })

    }

    private suspend fun getAffirmation() {
        if (AffirmationPref.getAffirmationEnabled(this)) {
            AffirmationManager.getAffirmations {
                affirmations.value = it
            }
        }
    }
}

