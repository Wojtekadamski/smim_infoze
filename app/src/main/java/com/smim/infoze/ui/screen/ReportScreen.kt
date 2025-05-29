package com.smim.infoze.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.smim.infoze.R
import com.smim.infoze.ui.component.BottomNavigationBar
import com.smim.infoze.ui.component.FullscreenImageViewer

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreen(navController: NavController) {
    val imageNames = listOf("graph1", "graph2", "graph3", "graph4", "graph5", "graph6", "graph7", "graph8", "graph9", "graph10")
    var selectedImage by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Raporty i Analizy") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF07441F),
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(imageNames) { name ->
                    Image(
                        painter = painterResource(id = getDrawableIdByName(name)),
                        contentDescription = name,
                        modifier = Modifier
                            .size(80.dp)
                            .clickable { selectedImage = name }
                    )
                }
            }

            selectedImage?.let { imageName ->
                val startIndex = imageNames.indexOf(imageName)
                FullscreenImageViewer(
                    imageNames = imageNames,
                    startIndex = startIndex,
                    onDismiss = { selectedImage = null },
                    getDrawableIdByName = { getDrawableIdByName(it) }
                )
            }
        }
    }
}

@Composable
fun getDrawableIdByName(name: String): Int {
    val context = LocalContext.current
    return remember(name) {
        context.resources.getIdentifier(name, "drawable", context.packageName)
    }
}
