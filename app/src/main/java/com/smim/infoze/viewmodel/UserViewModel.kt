package com.smim.infoze.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.smim.infoze.data.AppDatabase
import com.smim.infoze.data.User
import com.smim.infoze.data.UserPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao = AppDatabase.getDatabase(application).userDao()
    private val prefs = UserPreferences(application)

    fun registerUser(user: User, onResult: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val existingUser = userDao.getUserByEmail(user.email)
            if (existingUser == null) {
                userDao.insert(user)
                withContext(Dispatchers.Main) {
                    onResult(true)
                }
            } else {
                withContext(Dispatchers.Main) {
                    onResult(false)
                }
            }
        }
    }

    fun loginUser(email: String, password: String, remember: Boolean = false, onResult: (User?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userDao.login(email, password)
            withContext(Dispatchers.Main) {
                if (user != null) {
                    val prefs = UserPreferences(getApplication())
                    if (remember) {
                        prefs.saveUserData(user)
                    } else {
                        prefs.setLoggedInUser(user.email)
                        prefs.setTemporaryUsername(user.username)
                    }
                }
                onResult(user)
            }
        }
    }



    fun updateUserProfile(
        oldEmail: String,
        newUsername: String,
        newEmail: String,
        newPassword: String,
        onResult: (String) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val existingByEmail = if (newEmail != oldEmail) userDao.getUserByEmail(newEmail) else null
            val existingByUsername = if (newUsername != userDao.getUserByEmail(oldEmail)?.username) userDao.getUserByUsername(newUsername) else null

            when {
                existingByEmail != null -> withContext(Dispatchers.Main) { onResult("EMAIL_EXISTS") }
                existingByUsername != null -> withContext(Dispatchers.Main) { onResult("USERNAME_EXISTS") }
                else -> {
                    userDao.updateUserInfo(oldEmail, newUsername, newEmail, newPassword)
                    withContext(Dispatchers.Main) { onResult("OK") }
                }
            }
        }
    }

    fun updateUser(
        originalEmail: String,
        newUsername: String,
        newEmail: String,
        newPassword: String?,
        onResult: (Boolean, String) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val existingByUsername = userDao.getUserByUsername(newUsername)
            val existingByEmail = userDao.getUserByEmail(newEmail)
            val currentUser = userDao.getUserByEmail(originalEmail)

            if (currentUser == null) {
                withContext(Dispatchers.Main) {
                    onResult(false, "Użytkownik nie istnieje")
                }
                return@launch
            }

            if (newUsername != currentUser.username && existingByUsername != null) {
                withContext(Dispatchers.Main) {
                    onResult(false, "Nazwa użytkownika już istnieje")
                }
                return@launch
            }

            if (newEmail != currentUser.email && existingByEmail != null) {
                withContext(Dispatchers.Main) {
                    onResult(false, "Email już istnieje")
                }
                return@launch
            }

            val updatedUser = currentUser.copy(
                username = newUsername,
                email = newEmail,
                password = newPassword?.takeIf { it.isNotBlank() } ?: currentUser.password
            )

            userDao.updateUser(updatedUser)

            withContext(Dispatchers.Main) {
                onResult(true, "Zaktualizowano")
            }
        }
    }




}
