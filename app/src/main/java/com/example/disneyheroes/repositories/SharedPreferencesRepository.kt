package com.example.disneyheroes.repositories

import android.content.Context
import androidx.core.content.edit

private const val GLOBAL_PREFERENCES = "global_preferences"

class SharedPreferencesRepository(context: Context) {

    private val globalPreferences =
        context.getSharedPreferences(GLOBAL_PREFERENCES, Context.MODE_PRIVATE)

    fun setFavoriteHero(key: String, value: Boolean) {
        globalPreferences.edit {
            putBoolean(key, value)
        }
    }

    fun getFavoriteHero(key: String): Boolean {
        return globalPreferences.getBoolean(key, false)
    }
}