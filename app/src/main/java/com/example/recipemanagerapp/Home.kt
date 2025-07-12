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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import com.example.recipemanagerapp.components.CustomTopAppBar

// Data classes and sample data remain here
data class ProfileData(
    val name: String,
    val profilePicture: Int,
    val points: Int = 454,
    val cooked: Int? = 12,
    val contributed: Int? = 5,
    val followers: Int? = 100,
    val likes: Int? = 2000,
)


data class CategoryData(
    val name: String,
    val image: Int,
)

data class RecipeSteps(
    val stepNumber: Int,
    val description: String,
)

data class RecipeData(
    val id: Int, // Added ID to RecipeData for navigation
    val name: String,
    val image: Int,
    val description: String,
    val ingredients: List<String>,
    val preparationTime: String,
    val rating: Int,
    val steps: List<RecipeSteps>,
)

var profiles = listOf<ProfileData>(
    ProfileData(
        name = "Daniel",
        profilePicture = R.drawable.daniel
    ),
)

var maxRating = 5

var recipe = listOf<RecipeData>(
    RecipeData(
        id = 1, // Assign unique IDs
        name = "Spaghetti Carbonara",
        image = R.drawable.spaghettibolognese,
        description = "A classic Italian pasta dish made with eggs, cheese, pancetta, and pepper.",
        ingredients = listOf("Spaghetti", "Eggs", "Parmesan cheese", "Pancetta", "Black pepper"),
        preparationTime = "20 minutes",
        rating = 3,
        steps = listOf(
            RecipeSteps(1, "Boil the spaghetti in salted water."),
            RecipeSteps(2, "Fry the pancetta until crispy."),
            RecipeSteps(3, "Whisk eggs and mix with cheese."),
            RecipeSteps(4, "Combine everything and serve with black pepper.")
        )
    ),
    RecipeData(
        id = 2, // Assign unique IDs
        name = "Ceasar Salad",
        image = R.drawable.salads,
        description = "A fresh salad with romaine lettuce, croutons, and Caesar dressing.",
        ingredients = listOf("Romaine lettuce", "Croutons", "Caesar dressing", "Parmesan cheese"),
        preparationTime = "10 minutes",
        rating = 4,
        steps = listOf(
            RecipeSteps(1, "Chop the romaine lettuce."),
            RecipeSteps(2, "Add croutons and Parmesan cheese."),
            RecipeSteps(3, "Drizzle with Caesar dressing and toss.")
        )
    ),
    RecipeData(
        id = 3, // Assign unique IDs
        name = "Chipotle",
        image = R.drawable.chipotle,
        description = "A spicy Mexican dish with rice, beans, and your choice of meat.",
        ingredients = listOf("Rice", "Beans", "Chicken", "Salsa", "Guacamole"),
        preparationTime = "30 minutes",
        rating = 5,
        steps = listOf(
            RecipeSteps(1, "Cook the rice and beans."),
            RecipeSteps(2, "Grill the chicken with spices."),
            RecipeSteps(3, "Assemble with salsa and guacamole.")
        )
    ),
    RecipeData(
        id = 4, // Assign unique IDs
        name = "Churros",
        image = R.drawable.churros,
        description = "A sweet Spanish pastry, deep-fried and coated in sugar.",
        ingredients = listOf("Flour", "Sugar", "Eggs", "Cinnamon", "Oil for frying"),
        preparationTime = "25 minutes",
        rating = 2,
        steps = listOf(
            RecipeSteps(1, "Mix flour, sugar, and eggs to form a dough."),
            RecipeSteps(2, "Pipe the dough into hot oil and fry until golden."),
            RecipeSteps(3, "Coat in sugar and cinnamon before serving.")
        )
    ),
)

var categoryList = listOf<CategoryData>(
    CategoryData(
        name = "Lunch",
        image = R.drawable.lunch
    ),
    CategoryData(
        name = "Breakfast",
        image = R.drawable.breakfast
    ),
    CategoryData(
        name = "Dessert",
        image = R.drawable.desserts
    ),
    CategoryData(
        name = "Cocktails",
        image = R.drawable.cocktails
    ),
)

/**
 * The main composable for the Home screen.
 * @param navController The NavController to handle navigation actions.
 */
@Composable
fun HomeScreen(navController: NavController, onProfileClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(22, 22, 22)) // Dark background for the whole screen
            .verticalScroll(rememberScrollState())
            .padding(bottom = 80.dp)
    ) {
        CustomTopAppBar(
            onProfileClick = onProfileClick,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(30.dp))
        Hero(recipeData = recipe[0])
        Spacer(Modifier.height(24.dp))
        RandomRecipe()
        Spacer(Modifier.height(32.dp))
        LazyFoodCategory()
        Spacer(Modifier.height(32.dp))
        PopularRecipes(navController = navController)
    }
}

@Composable
fun Hero(recipeData: RecipeData) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(300.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        Image(
            painter = painterResource(id = recipeData.image),
            contentDescription = "Recipe Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.6f),
                            Color.Transparent
                        ),
                        startY = 0f,
                        endY = 850f
                    )
                )
        )
        {
            Column (
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(
                        text = "RECIPE OF THE DAY",
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = recipeData.preparationTime,
                        color = Color.LightGray,
                        fontSize = 12.sp,
                    )
                }
                Text(
                    text = recipeData.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = androidx.compose.material3.MaterialTheme.typography.titleLarge
                )
                Row{
                    repeat(recipeData.rating) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Filled Star Icon",
                            tint = Color(0xFFF0A800),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    repeat(maxRating - recipeData.rating) {
                        Icon(
                            imageVector = Icons.Outlined.Star,
                            contentDescription = "Empty Star Icon",
                            tint = Color.Gray,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RandomRecipe() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(70.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(40, 40, 40)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 12.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(255, 255, 255, 200)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dice),
                    contentDescription = "Random Recipe",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(30.dp)
                )
            }
            Column(modifier = Modifier.weight(1f).padding(horizontal = 16.dp)) {
                Text(
                    text = "Random recipe",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Take a chance on your next meal!",
                    color = Color.LightGray,
                    style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Arrow Icon to random recipe",
                tint = Color(0xFF6A54DC)
            )
        }
    }
}

@Composable
fun LazyFoodCategory() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Categories",
                color = Color.White,
                style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                "See all",
                color = Color(0xFF6A54DC),
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
            )
        }
        Spacer(Modifier.height(16.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(categoryList) { category ->
                Column(
                    modifier = Modifier.width(80.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Card(
                        modifier = Modifier.size(80.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(33, 33, 33))
                    ) {
                        Image(
                            painter = painterResource(id = category.image),
                            contentDescription = "Category Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Text(
                        text = category.name,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

/**
 * Composable function to display a list of popular recipes.
 * Now accepts NavController to enable navigation to detail screen.
 * @param navController The NavController to handle navigation.
 */
@Composable
fun PopularRecipes(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),

        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Popular right now 🔥",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            style = androidx.compose.material3.MaterialTheme.typography.titleMedium
        )
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            recipe.forEach { recipeData ->
                val interactionSource = remember { MutableInteractionSource() }
                val isPressed by interactionSource.collectIsPressedAsState()

                val iconAlpha by animateFloatAsState(
                    targetValue = if (isPressed) 0.5f else 1f,
                    animationSpec = tween(durationMillis = 150)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            navController.navigate("recipeDetail/${recipeData.id}")
                        },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(33, 33, 33))
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = painterResource(id = recipeData.image),
                            contentDescription = "Recipe Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(12.dp))
                        )
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = recipeData.name,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = recipeData.description,
                                maxLines = 2,
                                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                                color = Color.Gray
                            )
                        }
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "Arrow Icon to recipe details",
                            tint = Color(0xFF6A54DC),
                            modifier = Modifier.alpha(iconAlpha)
                        )

                    }
                }
            }
        }
    }
}


/**
 * Data class to represent a navigation item.
 */
data class NavigationItem(
    val route: String,
    val selectedIcon: androidx.compose.ui.graphics.vector.ImageVector,
    val unselectedIcon: androidx.compose.ui.graphics.vector.ImageVector,
    val contentDescription: String
)
