package com.example.recipemanagerapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * The main composable for the Favorites screen.
 */
@Composable
fun FavoriteScreen() {
    // A simple container to center the text.
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        TextComp()
    }
}

/**
 * A simple text component for the favorites page.
 */
@Composable
fun TextComp() {
    Text(
        text = "This is the favorite recipes page. You can add your favorite recipes here.",
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(16.dp)
    )
}