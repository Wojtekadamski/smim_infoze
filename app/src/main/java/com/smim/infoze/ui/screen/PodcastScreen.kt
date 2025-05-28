package com.smim.infoze.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.smim.infoze.model.Creator
import com.smim.infoze.model.Material
import com.smim.infoze.model.MaterialType
import com.smim.infoze.ui.component.BottomNavigationBar
import com.smim.infoze.ui.component.PodcastItem

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PodcastScreen(navController: NavController) {
    val allMaterials = Material.sampleMaterials()
    val allCreators = Creator.sampleCreators()
    val podcastMaterials = allMaterials.filter { it.type == MaterialType.AUDIO }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Podcasty") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = paddingValues.calculateTopPadding() + 16.dp,
                bottom = paddingValues.calculateBottomPadding() + 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(podcastMaterials) { material ->
                val creator = allCreators.find { it.id == material.creatorId }
                if (creator != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(
                                elevation = 4.dp,
                                shape = RoundedCornerShape(12.dp),
                                ambientColor = Color.Gray,
                                spotColor = Color.DarkGray
                            )
                            .background(
                                color = Color(0xFFE8F5E9), // delikatny zielonkawy
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(12.dp)
                    ) {
                        PodcastItem(material = material, creator = creator)
                    }
                }
            }
        }
    }
}
