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
            if (user != null && remember) {
                UserPreferences(getApplication()).saveUserData(user)
            } else if (user != null) {
                UserPreferences(getApplication()).setLoggedInUser(user.email)
            }
            withContext(Dispatchers.Main) {
                onResult(user)
            }
        }
    }


}
