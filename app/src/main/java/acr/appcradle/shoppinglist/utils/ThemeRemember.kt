package acr.appcradle.shoppinglist.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext

const val THEME_PREFS_KEY = "app_theme_key"

internal enum class ThemeOption { SYSTEM, DARK, LIGHT }

internal class ThemeRemember(
    @ApplicationContext private val context: Context
) {

    val prefs: SharedPreferences = context.getSharedPreferences("prefs", MODE_PRIVATE)

    fun saveTheme(value: ThemeOption) = prefs.edit { putInt(THEME_PREFS_KEY, value.ordinal) }

    fun getTheme(): ThemeOption {
        val value = prefs.getInt(THEME_PREFS_KEY, 0)
        return when (value) {
            0 -> ThemeOption.SYSTEM
            1 -> ThemeOption.DARK
            else -> ThemeOption.LIGHT
        }
    }
}
