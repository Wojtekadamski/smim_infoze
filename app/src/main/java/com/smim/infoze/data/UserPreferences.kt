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





    fun clearUser() {
        prefs.edit().clear().apply()
    }
}
