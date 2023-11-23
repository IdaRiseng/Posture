package no.sporty.posture.sharedPreferences

import android.content.Context


object InfoCardPrefs {
    private const val THANK_YOU_CARD = "thank_you_card"
    private const val CREATE_CUSTOM_EXERCISE_CARD = "create_custom_exercise_card"

    fun thankYouCardDismissed(context: Context) {
        getSharedPreferences(context).edit().putBoolean(THANK_YOU_CARD, true).apply()
    }

    fun isThankYouCardDismissed(context: Context): Boolean = getSharedPreferences(context).getBoolean(THANK_YOU_CARD, false)

    fun customExerciseCardDismissed(context: Context) {
        getSharedPreferences(context).edit().putBoolean(CREATE_CUSTOM_EXERCISE_CARD, true).apply()
    }

    fun isCustomExerciseCarDismissed(context: Context): Boolean = getSharedPreferences(context).getBoolean(CREATE_CUSTOM_EXERCISE_CARD, false)


}