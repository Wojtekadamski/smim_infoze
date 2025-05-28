package com.smim.infoze.ui.screen

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.smim.infoze.data.User
import com.smim.infoze.viewmodel.UserViewModel
import com.smim.infoze.viewmodel.ViewModelFactory

@Composable
fun RegisterScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: UserViewModel = viewModel(
        factory = ViewModelFactory(context.applicationContext as Application)
    )
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var acceptedTerms by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Zielone kółka dekoracyjne
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color(0xFFB5D8A6), shape = CircleShape)
                ) {}
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color(0xFF89B482), shape = CircleShape)
                ) {}
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Welcome to", fontSize = 20.sp, color = Color.DarkGray)
            Text(
                "InfoZE",
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF154C1B)
            )
            Text(
                text = "Dołącz do społeczności i bądź na bieżąco!",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            val textFieldModifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp)

            OutlinedTextField(value = username, onValueChange = { username = it }, label = { Text("nazwa użytkownika") }, modifier = textFieldModifier)
            OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("adres email") }, modifier = textFieldModifier)
            OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("numer telefonu") }, modifier = textFieldModifier)
            OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("hasło") }, modifier = textFieldModifier)
            OutlinedTextField(value = confirmPassword, onValueChange = { confirmPassword = it }, label = { Text("powtórz hasło") }, modifier = textFieldModifier)

            Spacer(modifier = Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = acceptedTerms, onCheckedChange = { acceptedTerms = it })
                Text(text = "I accept the terms & conditions", fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    val newUser = User(
                        username = username,
                        email = email,
                        phone = phone,
                        password = password
                    )
                    viewModel.registerUser(newUser) { success ->
                        if (success) {
                            Toast.makeText(context, "Zarejestrowano!", Toast.LENGTH_SHORT).show()
                            navController.navigate("login")
                        } else {
                            Toast.makeText(context, "Użytkownik już istnieje", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF39B54A)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("Rejestracja", color = Color.White)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row {
                Text("Masz już konto? ", color = Color.Gray)
                TextButton(onClick = { navController.navigate("login") }) {
                    Text("Zaloguj się", color = Color(0xFF007AFF))
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Dekoracja dolna
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color(0xFFB5D8A6), shape = CircleShape)
                ) {}
            }
        }
    }
}
