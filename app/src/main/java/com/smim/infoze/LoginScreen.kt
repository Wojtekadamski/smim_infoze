package com.smim.infoze

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.smim.infoze.data.UserPreferences
import com.smim.infoze.ui.theme.GreenDark
import com.smim.infoze.viewmodel.UserViewModel
import com.smim.infoze.viewmodel.ViewModelFactory
@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: UserViewModel = viewModel(
        factory = ViewModelFactory(context.applicationContext as Application)
    )

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .height(100.dp)
                .padding(bottom = 16.dp)
        )

        Text(
            text = "Logowanie",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = GreenDark
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Hasło") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = rememberMe,
                onCheckedChange = { rememberMe = it },
                colors = CheckboxDefaults.colors(checkedColor = GreenDark)
            )
            Text("Zapamiętaj mnie")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.loginUser(email, password, rememberMe) { user ->
                    if (user != null) {
                        val prefs = UserPreferences(context)
                        if (rememberMe) {
                            prefs.saveUser(user.username, email, password)
                        } else {
                            prefs.setLoggedInUser(user.email)
                            prefs.setTemporaryUsername(user.username)

                        }

                        Toast.makeText(context, "Zalogowano jako ${user.username}", Toast.LENGTH_SHORT).show()
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    } else {
                        Toast.makeText(context, "Nieprawidłowy login lub hasło", Toast.LENGTH_SHORT).show()
                    }
                }

            },
            colors = ButtonDefaults.buttonColors(containerColor = GreenDark),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text("Zaloguj się", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        ClickableText(
            text = AnnotatedString("Nie masz konta? Zarejestruj się"),
            onClick = { navController.navigate("register") },
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
