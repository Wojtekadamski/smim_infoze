package com.smim.infoze.ui.screen

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.smim.infoze.R
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
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Dekoracyjne tło
        Image(
            painter = painterResource(id = R.drawable.shapes),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nagłówek
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Welcome to",
                fontSize = 20.sp,
                color = Color(0xFF1D4110),
                fontWeight = FontWeight.Normal
            )
            Text(
                text = "InfOZE",
                modifier = Modifier
                    .width(278.dp)
                    .height(82.dp),
                style = TextStyle(
                    fontSize = 64.sp,
                    fontFamily = FontFamily(Font(R.font.rammetto_one)),
                    fontWeight = FontWeight.W400,
                    color = Color(0xFF1D4110),
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Załóż konto i bądź na bieżąco!",
                fontSize = 16.sp,
                color = Color(0xFF1D4110),
                textAlign = TextAlign.Center
            )
        }

        // Obrazek telefonu
        Image(
            painter = painterResource(id = R.drawable.loginimg),
            contentDescription = null,
            modifier = Modifier
                .width(334.dp)
                .height(265.dp)
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("nazwa użytkownika") },
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(4.dp, RoundedCornerShape(50)),
                shape = RoundedCornerShape(50)
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("**********") },
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(4.dp, RoundedCornerShape(50)),
                shape = RoundedCornerShape(50),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // ✅ Checkbox "Zapamiętaj mnie"
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = rememberMe,
                    onCheckedChange = { rememberMe = it },
                    colors = CheckboxDefaults.colors(checkedColor = GreenDark)
                )
                Text(
                    "Zapamiętaj mnie",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF1D4110)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

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
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF39B54A)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Login", fontSize = 16.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(12.dp))

            ClickableText(
                text = AnnotatedString("Don’t have an account? Register"),
                onClick = { navController.navigate("register") },
                modifier = Modifier.padding(top = 8.dp),
                style = LocalTextStyle.current.copy(color = Color(0xFF1D4110))
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
