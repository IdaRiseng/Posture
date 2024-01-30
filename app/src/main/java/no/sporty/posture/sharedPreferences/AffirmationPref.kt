package no.sporty.posture.sharedPreferences

import android.content.Context

object AffirmationPref {
    private const val AFFIRMATION_ENABLED = "affirmation_enabled"

    fun saveAffirmationEnabled(context: Context, enabled: Boolean) {
        getSharedPreferences(context).edit().putBoolean(AFFIRMATION_ENABLED, enabled).apply()
    }

    fun getAffirmationEnabled(context: Context) = getSharedPreferences(context).getBoolean(AFFIRMATION_ENABLED, false)
}