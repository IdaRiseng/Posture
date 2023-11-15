package no.sporty.posture.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

fun getSharedPreferences(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
