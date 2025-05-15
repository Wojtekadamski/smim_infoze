package com.smim.infoze

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.smim.infoze.data.UserPreferences
import com.smim.infoze.ui.component.BottomNavigationBar
import com.smim.infoze.ui.theme.GreenDark
import java.io.InputStream
import coil.compose.AsyncImage
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(navController: NavController) {
    val context = LocalContext.current
    val prefs = remember { UserPreferences(context) }
    val user = prefs.getSavedUserData()
    var avatarUri by remember { mutableStateOf<String?>(prefs.getAvatarUri(user?.email ?: "")) }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            prefs.saveAvatarUri(user?.email ?: "", it.toString())
            avatarUri = it.toString()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = GreenDark)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = GreenDark)
            )
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(GreenDark),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .clickable { launcher.launch("image/*") }
                    ) {
                        avatarUri?.let {
                            AsyncImage(
                                model = ImageRequest.Builder(context)
                                    .data(Uri.parse(avatarUri))
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "Avatar",
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape)
                            )
                        } ?: Image(
                            painter = painterResource(id = R.drawable.avatar_placeholder),
                            contentDescription = "Default Avatar"
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                    Text(text = user?.username ?: "", color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold)
                    Text(text = user?.email ?: "", color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f), fontSize = MaterialTheme.typography.bodySmall.fontSize)
                }
            }

            Spacer(Modifier.height(24.dp))

            Column(Modifier.padding(horizontal = 24.dp)) {
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Text("Challenge Points: +1000")
                }
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Text("Pomoc & Wsparcie")
                }
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Text("Ochrona prywatności")
                }
                Button(
                    onClick = { navController.navigate("edit_profile") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Text("Ustawienia profilu")
                }
            }
        }
    }
}
