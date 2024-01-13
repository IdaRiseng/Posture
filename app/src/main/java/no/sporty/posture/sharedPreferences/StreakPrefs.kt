package no.sporty.posture.sharedPreferences

import android.content.Context
import com.google.gson.reflect.TypeToken
import java.time.LocalDate

data class SavedExerciseInfo(
    val name: String? = null,
    val date: LocalDate
)

object StreakPrefs {
    //List of dates
    //if date already exist in list, don't save the date.
    //if previous date is recorded, add 1 streak.

    private const val EXERCISE_DATE_LIST = "exercise_date_list"
    private const val EXERCISE_STREAK = "exercise_streak"

    fun saveDate(context: Context, date: SavedExerciseInfo) {
        //save date
        val oldDates = getDateList(context)
        val newDates = oldDates.toMutableList().apply { add(date) }
        val newDatesDistinct = newDates.distinct()

        getSharedPreferences(context).edit().putString(EXERCISE_DATE_LIST, GSON.toJson(newDatesDistinct)).apply()

        //Save Streak
        if (!oldDates.contains(date)) {
            saveStreak(getStreak(context) + 1, context)
        }
    }

    fun getDateList(context: Context): List<SavedExerciseInfo> {
        val json = getSharedPreferences(context).getString(EXERCISE_DATE_LIST, null)
        return GSON.fromJson(json, object : TypeToken<List<SavedExerciseInfo>?>() {}.type) ?: emptyList()
    }

    fun getStreak(context: Context): Int = getSharedPreferences(context).getInt(EXERCISE_STREAK, 0)

    fun getTotal(context: Context) = getDateList(context).size

    private fun saveStreak(streak: Int, context: Context) {
        getSharedPreferences(context).edit().putInt(EXERCISE_STREAK, streak).apply()
    }

    fun checkStreakLogin(context: Context) {
        val previousDate = getDateList(context).lastOrNull() ?: return
        if (LocalDate.now().isAfter(previousDate.date.plusDays(1))) {
            saveStreak(0, context)
        }
    }
}