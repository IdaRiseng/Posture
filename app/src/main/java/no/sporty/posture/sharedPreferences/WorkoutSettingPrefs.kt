package no.sporty.posture.sharedPreferences

import android.content.Context
import com.google.gson.reflect.TypeToken
import no.sporty.posture.model.WorkoutSetting

object WorkoutSettingPrefs {
    private const val WORKOUT_OPTION = "workout_option"
    private const val TIME_BASED_WORKOUT = "time_based_workout"
    private const val REP_BASED_WORKOUT = "rep_based_workout"

    fun saveWorkoutOption(context: Context, workoutSetting: WorkoutSetting) {
        getSharedPreferences(context).edit().putString(WORKOUT_OPTION, GSON.toJson(workoutSetting)).apply()
    }

    fun getWorkoutOption(context: Context): WorkoutSetting {
        val json = getSharedPreferences(context).getString(WORKOUT_OPTION, null)
        return GSON.fromJson(json, object : TypeToken<WorkoutSetting?>() {}.type) ?: WorkoutSetting()
    }

    fun saveTimeBasedWorkout(context: Context, time: Float) {
        getSharedPreferences(context).edit().putFloat(TIME_BASED_WORKOUT, time).apply()
    }

    fun getTimeBasedWorkout(context: Context) = getSharedPreferences(context).getFloat(TIME_BASED_WORKOUT, 1f)


    fun saveRepBasedWorkout(context: Context, rep: Float) {
        getSharedPreferences(context).edit().putFloat(REP_BASED_WORKOUT, rep).apply()
    }

    fun getRepBasedWorkout(context: Context) = getSharedPreferences(context).getFloat(REP_BASED_WORKOUT, 4f)

}