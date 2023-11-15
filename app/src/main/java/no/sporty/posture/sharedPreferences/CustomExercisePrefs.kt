package no.sporty.posture.sharedPreferences

import android.content.Context
import com.google.gson.reflect.TypeToken
import no.sporty.posture.model.CustomExercise

object CustomExercisePrefs {
    private const val CUSTOM_EXERCISE_LIST = "custom_exercise_list"

    fun saveCustomExerciseList(context: Context, customExercise: CustomExercise) {
        val oldList = getCustomExerciseList(context)
        val newDates = oldList.toMutableList().apply { add(customExercise) }
        val newDatesDistinct = newDates.distinct()

        getSharedPreferences(context).edit().putString(CUSTOM_EXERCISE_LIST, GSON.toJson(newDatesDistinct)).apply()
    }

    fun getCustomExerciseList(context: Context): List<CustomExercise> {
        val json = getSharedPreferences(context).getString(CUSTOM_EXERCISE_LIST, null)
        return GSON.fromJson(json, object : TypeToken<List<CustomExercise>?>() {}.type) ?: emptyList()
    }
}