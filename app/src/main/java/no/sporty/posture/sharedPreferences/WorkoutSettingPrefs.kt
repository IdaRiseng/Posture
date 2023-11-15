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

    fun saveTimeBasedWorkout(context: Context, time: Int) {
        getSharedPreferences(context).edit().putInt(TIME_BASED_WORKOUT, time).apply()
    }

    fun getTimeBasedWorkout(context: Context) = getSharedPreferences(context).getInt(TIME_BASED_WORKOUT, 1)


    fun saveRepBasedWorkout(context: Context, rep: Int) {
        getSharedPreferences(context).edit().putInt(REP_BASED_WORKOUT, rep).apply()
    }

    fun getRepBasedWorkout(context: Context) = getSharedPreferences(context).getInt(REP_BASED_WORKOUT, 4)

}