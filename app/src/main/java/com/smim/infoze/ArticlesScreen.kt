package com.smim.infoze

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.smim.infoze.data.UserPreferences
import com.smim.infoze.model.Material
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticlesScreen(navController: NavController) {
    val context = LocalContext.current
    val userPrefs = remember { UserPreferences(context) }
    val savedUser = userPrefs.getSavedUserData()
    val loggedEmail = savedUser?.email ?: userPrefs.getLoggedInUser()
    val favoriteCreatorIds = loggedEmail?.let { userPrefs.getFavoriteCreators(it) } ?: emptySet()

    val allMaterials = remember { Material.sampleMaterials() }
    val articles = allMaterials
        .filter { it.type.name == "ARTICLE" }
        .sortedByDescending { it.publishDate }

    val favoriteArticles = articles.filter { favoriteCreatorIds.contains(it.creatorId) }.take(3)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Artykuły") }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            if (favoriteArticles.isNotEmpty()) {
                Text("Ulubione", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(16.dp))
                favoriteArticles.forEach {
                    ArticleCard(it)
                }
            }

            Text("Wszystkie", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(16.dp))
            articles.forEach {
                ArticleCard(it)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ArticleCard(article: Material) {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = article.thumbnailRes),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(article.title, fontWeight = FontWeight.Bold)
                Text(article.description, maxLines = 2)
                Text("Data publikacji: ${article.publishDate.format(formatter)}", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
