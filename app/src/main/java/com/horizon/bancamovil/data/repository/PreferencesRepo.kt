package com.horizon.bancamovil.data.repository

import android.content.SharedPreferences
import com.horizon.bancamovil.constans.KEY_THEME_COLOR
import com.horizon.bancamovil.domain.state.DataUser
import javax.inject.Inject

class PreferencesRepo @Inject constructor(
    private val prefs: SharedPreferences,
    private val editor: SharedPreferences.Editor
) {

    private val user = DataUser()

    fun saveModeTheme(isDarkTheme: Boolean) {
        editor.putBoolean(KEY_THEME_COLOR, isDarkTheme).apply()
    }

    fun getModeTheme() : Boolean {
        return prefs.getBoolean(KEY_THEME_COLOR, user.darkMode)
    }

}