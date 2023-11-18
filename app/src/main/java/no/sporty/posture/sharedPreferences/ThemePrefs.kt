package no.sporty.posture.sharedPreferences

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

object ThemePrefs {
    private const val SELECTED_THEME = "selected_theme"
    private const val CHRISTMAS_THEME = "christmas_theme"
    /* Notice that we don't save any value if default is selected,
     * thus supporting MODE_NIGHT_AUTO_BATTERY, MODE_NIGHT_AUTO_TIME and MODE_NIGHT_AUTO */

    /**
     * @return selected night mode value from AppCompatDelegate,
     * or null if none is selected (default)
     */
    fun readSelectedTheme(context: Context): Int? {
        val prefs = getSharedPreferences(context)
        return if (prefs.contains(SELECTED_THEME)) {
            getSharedPreferences(context)
                .getInt(SELECTED_THEME, AppCompatDelegate.MODE_NIGHT_UNSPECIFIED) // default should never be read
        } else if (prefs.contains("dark_mode")) { // old regime from v5.11
            if (getSharedPreferences(context).getBoolean("dark_mode", false)) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            }
        } else null
    }

    /**
     * Write selected theme. Null will remove value, thus acting as default.
     */
    fun writeSelectedTheme(context: Context, value: Int?) {
        if (value == null) {
            getSharedPreferences(context)
                .edit()
                .remove(SELECTED_THEME)
                .remove("dark_mode") // old regime from v5.11
                .apply()
        } else {
            getSharedPreferences(context)
                .edit()
                .putInt(SELECTED_THEME, value)
                .remove("dark_mode") // old regime from v5.11
                .apply()
        }
    }
}