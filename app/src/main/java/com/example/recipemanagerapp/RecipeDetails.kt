package com.example.recipemanagerapp

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// Consistent colors from your Home.kt for a cohesive look.
private val accentPurple = Color(0xFF6A54DC)
private val darkBackground = Color(22, 22, 22)
private val cardBackground = Color(33, 33, 33)
private val accentYellow = Color(0xFFF0A800)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(navController: NavController, recipeId: Int?) {
    // Default to the first recipe if the ID is not found.
    val recipeData = recipe.find { it.id == recipeId } ?: recipe.first()

    // Create an InteractionSource for the IconButton
    val interactionSource = remember { MutableInteractionSource() }
    // Observe if the button is currently pressed
    val isPressed by interactionSource.collectIsPressedAsState()

    // Animate the alpha of the icon based on press state
    val iconAlpha by animateFloatAsState(
        targetValue = if (isPressed) 0.5f else 1f, // Fade to 50% opacity when pressed
        animationSpec = tween(durationMillis = 150) // Quick fade animation
    )

    Scaffold(
        topBar = {
            // Use CenterAlignedTopAppBar for automatic centering.
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = recipeData.name,
                        maxLines = 1,
                        fontWeight = FontWeight.Bold,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        // Provide the interaction source to the IconButton
                        interactionSource = interactionSource,
                        modifier = Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null // Disable the ripple indication here
                        ) {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Back",
                            tint = accentPurple, // Use consistent accent color.
                            // Apply the animated alpha here
                            modifier = Modifier.alpha(iconAlpha)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = darkBackground,
                    titleContentColor = Color.White
                )
            )
        },
        containerColor = darkBackground
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // --- Recipe Image Header ---
            // Reuses the Hero style from the home page.
            RecipeImageHeader(recipe = recipeData)

            // --- Details, Ingredients, and Steps ---
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Description section in its own card.
                InfoCard(title = "Description") {
                    Text(
                        text = recipeData.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.LightGray,
                        lineHeight = 24.sp
                    )
                }

                // Ingredients section in its own card.
                InfoCard(title = "Ingredients") {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        recipeData.ingredients.forEach { ingredient ->
                            IngredientRow(text = ingredient)
                        }
                    }
                }

                // Preparation steps in their own card.
                InfoCard(title = "Preparation") {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        recipeData.steps.forEach { step ->
                            StepItem(step = step)
                        }
                    }
                }
            }
        }
    }
}

// --- Reusable Composables Styled After Home.kt ---

/**
 * A wrapper Card styled like the cards on the home page.
 */
@Composable
private fun InfoCard(title: String, content: @Composable ColumnScope.() -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            content()
        }
    }
}

/**
 * Displays the main recipe image with a gradient, styled like the Hero component.
 */
@Composable
private fun RecipeImageHeader(recipe: RecipeData) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(250.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        Image(
            painter = painterResource(id = recipe.image),
            contentDescription = recipe.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        // Gradient overlay for text visibility.
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Black.copy(alpha = 0.6f), Color.Transparent),
                        startY = 0f,
                        endY = 850f
                    )
                )
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Prep time: ${recipe.preparationTime}",
                color = Color.LightGray,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
            Row {
                repeat(recipe.rating) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = accentYellow,
                        modifier = Modifier.size(18.dp)
                    )
                }
                repeat(maxRating - recipe.rating) {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

/**
 * A styled row for an ingredient, with a purple bullet point.
 */
@Composable
private fun IngredientRow(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(accentPurple)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.LightGray
        )
    }
}


/**
 * An item for a preparation step, using the purple accent color.
 */
@Composable
private fun StepItem(step: RecipeSteps) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "${step.stepNumber}",
            color = accentPurple,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .background(accentPurple.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                .padding(horizontal = 10.dp, vertical = 6.dp)
        )
        Text(
            text = step.description,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.LightGray,
            lineHeight = 24.sp,
            modifier = Modifier.padding(top = 6.dp)
        )
    }
}