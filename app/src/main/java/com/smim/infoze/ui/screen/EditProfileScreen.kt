package com.smim.infoze.ui.screen

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.smim.infoze.data.UserPreferences
import com.smim.infoze.ui.component.BottomNavigationBar
import com.smim.infoze.viewmodel.UserViewModel
import com.smim.infoze.viewmodel.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: UserViewModel = viewModel(factory = ViewModelFactory(context.applicationContext as Application))
    val prefs = remember { UserPreferences(context) }
    val savedUser = prefs.getSavedUserData() ?: return

    var username by remember { mutableStateOf(savedUser.username) }
    var email by remember { mutableStateOf(savedUser.email) }
    var password by remember { mutableStateOf("") }

    var showUsernameField by remember { mutableStateOf(false) }
    var showEmailField by remember { mutableStateOf(false) }
    var showPasswordField by remember { mutableStateOf(false) }
    val isHighContrast = MaterialTheme.colorScheme.primary == Color.Yellow


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edytuj profil", color = if (isHighContrast) Color.White else MaterialTheme.colorScheme.onSecondary) },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("accessibility_settings")
                    }) {
                        Icon(Icons.Default.Settings, contentDescription = "Ustawienia dostępności", tint = if (isHighContrast) Color.White else MaterialTheme.colorScheme.onSecondary)
                    }
                }
            )
                 },
        bottomBar = { BottomNavigationBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text("Nazwa użytkownika: ${savedUser.username}")
            if (showUsernameField) {
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Nowa nazwa użytkownika") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Button(
                onClick = { showUsernameField = !showUsernameField },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF39B54A)),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text("Zmień nazwę", color = Color.White)
            }

            Spacer(Modifier.height(16.dp))

            Text("Adres email: ${savedUser.email}")
            if (showEmailField) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Nowy adres email") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Button(
                onClick = { showEmailField = !showEmailField },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF39B54A)),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text("Zmień email", color = Color.White)
            }

            Spacer(Modifier.height(16.dp))

            if (showPasswordField) {
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Nowe hasło") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Button(
                onClick = { showPasswordField = !showPasswordField },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF39B54A)),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text("Zmień hasło", color = Color.White)
            }

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    viewModel.updateUser(
                        originalEmail = savedUser.email,
                        newUsername = username,
                        newEmail = email,
                        newPassword = password
                    ) { success, message ->
                        if (success) {
                            prefs.saveUser(username, email, password.ifBlank { savedUser.password })
                            Toast.makeText(context, "Zapisano zmiany", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF154C1B)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Zapisz zmiany", color = Color.White)
            }
        }
    }
}
