package com.smim.infoze.data

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveUser(username: String, email: String, password: String) {
        prefs.edit().apply {
            putString("username", username)
            putString("email", email)
            putString("password", password)
            apply()
        }
    }

    fun getSavedUser(): Triple<String, String, String>? {
        val username = prefs.getString("username", null)
        val email = prefs.getString("email", null)
        val password = prefs.getString("password", null)
        return if (username != null && email != null && password != null) {
            Triple(username,email, password)
        } else {
            null
        }
    }

    fun saveUserData(user: User) {
        prefs.edit().apply {
            putString("username", user.username)
            putString("email", user.email)
            putString("password", user.password)
            putString("logged_in_user", user.email)
            apply()
        }
    }


    fun getSavedUserData(): User? {
        val username = prefs.getString("username", null)
        val email = prefs.getString("email", null)
        val password = prefs.getString("password", null)
        return if (username != null && email != null && password != null) {
            User(username = username, email = email, password = password, phone = "") // phone opcjonalnie
        } else {
            null
        }
    }

    fun setLoggedInUser(email: String) {
        prefs.edit().putString("logged_in_user", email).apply()
    }

    fun clearLoginDataOnly() {
        prefs.edit().remove("username").remove("password").remove("logged_in_user").apply()
    }

    fun getLoggedInUser(): String? {
        return prefs.getString("logged_in_user", null)
    }

    fun setTemporaryUsername(username: String) {
        prefs.edit().putString("temp_username", username).apply()
    }

    fun getTemporaryUsername(): String? {
        return prefs.getString("temp_username", null)
    }
    fun saveFavorites(email: String, favorites: List<Int>) {
        prefs.edit().putStringSet("favorites_$email", favorites.map { it.toString() }.toSet()).apply()
    }

    fun getFavorites(email: String): List<Int> {
        return prefs.getStringSet("favorites_$email", emptySet())
            ?.mapNotNull { it.toIntOrNull() } ?: emptyList()
    }

    fun saveFavoriteCreators(email: String, creatorIds: Set<String>) {
        prefs.edit().putStringSet("favorites_$email", creatorIds).apply()
    }

    fun getFavoriteCreators(email: String): Set<String> {
        return prefs.getStringSet("favorites_$email", emptySet()) ?: emptySet()
    }

    fun countUsersWithFavorite(context: Context, creatorId: String): Int {
        val allPrefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE).all
        return allPrefs.entries.count { (key, value) ->
            key.startsWith("favorites_") && value is Set<*> && value.contains(creatorId)
        }
    }

    fun saveAvatarUri(email: String, uri: String) {
        prefs.edit().putString("avatar_$email", uri).apply()
    }

    fun getAvatarUri(email: String): String? {
        return prefs.getString("avatar_$email", null)
    }

    fun saveAccessibilitySettings(email: String, darkMode: Boolean, highContrast: Boolean, fontScale: Float) {
        prefs.edit().apply {
            putBoolean("dark_mode_$email", darkMode)
            putBoolean("high_contrast_$email", highContrast)
            putFloat("font_scale_$email", fontScale)
            apply()
        }
    }

    fun getDarkMode(email: String): Boolean {
        return prefs.getBoolean("dark_mode_$email", false)
    }

    fun getHighContrast(email: String): Boolean {
        return prefs.getBoolean("high_contrast_$email", false)
    }

    fun getFontScale(email: String): Float {
        return prefs.getFloat("font_scale_$email", 1.0f)
    }






    fun clearUser() {
        prefs.edit().clear().apply()
    }
}
