package com.example.recipes.ui.recipes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.recipes.data.model.Recipe
import com.example.recipes.data.remote.RemoteDataSource
import com.example.recipes.data.repository.RecipeRepository

@Composable
fun RecipeDetailScreen(recipeId: Int) {
    val recipe = remember { mutableStateOf<Recipe?>(null) }

    LaunchedEffect(recipeId) {
        val repo = RecipeRepository(RemoteDataSource.api)
        recipe.value = repo.getRecipeById(recipeId)
    }

    recipe.value?.let { recipe ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 32.dp)
        ) {
            Text(
                text = recipe.title,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            AsyncImage(
                model = recipe.image,
                contentDescription = recipe.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(Modifier.height(16.dp))

            recipe.cuisine?.let {
                Text("Cuisine: $it", style = MaterialTheme.typography.bodyMedium)
            }

            Row(
                modifier = Modifier.padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                recipe.servings?.let {
                    IconWithText(icon = Icons.Default.Person, text = "$it servings")
                }
                recipe.prepTime?.let {
                    IconWithText(icon = Icons.Default.Notifications, text = "Prep: $it")
                }
                recipe.cookTime?.let {
                    IconWithText(icon = Icons.Default.Notifications, text = "Cook: $it")
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

            recipe.ingredients?.let {
                Text("Ingredients", style = MaterialTheme.typography.titleMedium)
                Text(it, modifier = Modifier.padding(top = 4.dp))
            }

            Spacer(modifier = Modifier.height(12.dp))

            recipe.directions?.let {
                Text("Directions", style = MaterialTheme.typography.titleMedium)
                Text(it, modifier = Modifier.padding(top = 4.dp))
            }
        }
    } ?: Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}