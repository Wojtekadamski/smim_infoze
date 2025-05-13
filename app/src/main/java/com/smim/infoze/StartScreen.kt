package com.smim.infoze

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.smim.infoze.data.UserPreferences
import com.smim.infoze.ui.theme.GreenDark

@Composable
fun StartScreen(navController: NavController) {
    val context = LocalContext.current
    val userPrefs = remember { UserPreferences(context) }

    LaunchedEffect(Unit) {
        val savedUser = userPrefs.getSavedUser()
        if (savedUser != null) {
            navController.navigate("home") {
                popUpTo("start") { inclusive = true }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo), // upewnij się że plik nazywa się `logo.png` w res/drawable
            contentDescription = "Logo",
            modifier = Modifier
                .height(140.dp)
                .padding(bottom = 32.dp)
        )

        // Tytuł
        Text(
            text = "InfoZE",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = GreenDark
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Podtytuł
        Text(
            text = "Wiedza o energetyce odnawialnej zawsze pod ręką",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 16.dp),
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Przycisk „Zaczynamy”
        Button(
            onClick = { navController.navigate("login") },
            colors = ButtonDefaults.buttonColors(containerColor = GreenDark),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text(text = "Zaczynamy", fontSize = 16.sp)
        }
    }
}
