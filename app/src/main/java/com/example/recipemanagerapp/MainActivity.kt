package com.example.recipemanagerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.recipemanagerapp.ui.theme.RecipeManagerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipeManagerAppTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            // Pass the NavController and the current route to the nav bar
            CustomNavBar(navController = navController, currentRoute = currentRoute)
        }
    ) { innerPadding ->
        // Box to contain the NavHost, applying the inner padding from the Scaffold
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(navController = navController, startDestination = "home") {
                // Route for the Home screen
                composable("home") {
                    HomeScreen()
                }
                // Route for the Favorites screen
                composable("favorites") {
                    FavoriteScreen()
                }
                // Add other routes here as needed
                composable("notifications") {
                    // Placeholder for Notifications screen
                }
                composable("profile") {
                    // Placeholder for Profile screen
                }
            }
        }
    }
}

//// Declares the package name for the application.
//package com.example.recipemanagerapp
//
//// Import statements for various Android and Jetpack Compose components.
//// These lines bring in necessary classes and functions from different libraries.
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.aspectRatio
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.statusBarsPadding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.KeyboardArrowRight
//import androidx.compose.material.icons.filled.Person
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material.icons.filled.ShoppingCart
//import androidx.compose.material.icons.filled.Star
//import androidx.compose.material.icons.outlined.Home
//import androidx.compose.material.icons.outlined.Person
//import androidx.compose.material.icons.outlined.ShoppingCart
//import androidx.compose.material.icons.outlined.Star
//import androidx.compose.material3.Badge
//import androidx.compose.material3.BadgedBox
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.LocalRippleConfiguration
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.NavigationBar
//import androidx.compose.material3.NavigationBarItem
//import androidx.compose.material3.NavigationBarItemDefaults
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.CompositionLocalProvider
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.painter.Painter
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.currentBackStackEntryAsState
//import androidx.navigation.compose.rememberNavController
//import com.example.recipemanagerapp.ui.theme.RecipeManagerAppTheme
//
//// New imports for the modal and text field
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.draw.blur
//import androidx.compose.ui.window.Dialog
//import androidx.compose.ui.window.DialogProperties
//
//
////---
////## Data Structures
//
//// Defines a data class named **Recipe**. Data classes are simple classes used to hold data.
//data class Recipe(
//    // Unique identifier for the recipe.
//    val id: Int,
//    // Title of the recipe.
//    val title: String,
//    // Description of the recipe.
//    val description: String,
//    // Resource ID for the recipe's image (e.g., R.drawable.image_name).
//    val imageResId: Int,
//    // Rating of the recipe (e.g., out of 5 stars).
//    val rating: Int,
//    // Boolean indicating if this is the "Recipe of the Day," defaults to false.
//    val isRecipeOfTheDay: Boolean = false
//)
//
//// Defines a data class named **Category**.
//data class Category(
//    // Unique identifier for the category.
//    val id: Int,
//    // Name of the category.
//    val name: String,
//    // Resource ID for the category's image.
//    val imageResId: Int
//)
//
////---
////## Sample Data
//
//// Creates a list of **Recipe** objects, which serves as sample data for the app.
//val allRecipes = listOf(
//    // First recipe: Slow-Cooker Chicken Tortilla Soup.
//    Recipe(
//        id = 1,
//        title = "Slow-Cooker Chicken Tortilla Soup",
//        description = "A hearty and comforting soup perfect for cold days",
//        // References an image from the drawable resources.
//        imageResId = R.drawable.chipotle,
//        rating = 5,
//        // Marks this recipe as the "Recipe of the Day."
//        isRecipeOfTheDay = true
//    ),
//    // Second recipe: Big Breakfast.
//    Recipe(
//        id = 2,
//        title = "Big Breakfast",
//        description = "A hearty and filling start to your day.",
//        imageResId = R.drawable.breakfast,
//        rating = 5
//    ),
//    // Third recipe: Spaghetti Bolognese.
//    Recipe(
//        id = 3,
//        title = "Spaghetti Bolognese",
//        description = "Classic Italian comfort food.",
//        imageResId = R.drawable.spaghettibolognese,
//        rating = 4
//    ),
//    // Fourth recipe: Dessert Churros.
//    Recipe(
//        id = 4,
//        title = "Dessert Churros",
//        description = "Sweet, crispy, and perfect with chocolate.",
//        imageResId = R.drawable.churros,
//        rating = 4
//    )
//)
//
//// Creates a list of **Category** objects, providing sample category data.
//val categories = listOf(
//    Category(1, "Lunch", R.drawable.lunch),
//    Category(2, "Salads", R.drawable.salads),
//    Category(3, "Cocktails", R.drawable.cocktails),
//    Category(4, "Desserts", R.drawable.desserts)
//)
//
////---
////## Composable Functions - Building Blocks
//
//// Declares a composable function for displaying a row of categories.
//@Composable
//fun CategoryRow(categories: List<Category>) {
//    // **LazyRow** is a composable that displays a scrollable list of items horizontally,
//    // only composing and laying out items that are currently visible.
//    LazyRow(
//        // Arranges items horizontally with spacing between them.
//        horizontalArrangement = Arrangement.spacedBy(16.dp),
//        // Adds padding around the content of the lazy row.
//        contentPadding = PaddingValues(horizontal = 20.dp) // Consistent padding
//    ) {
//        // Iterates through the list of categories and composes an item for each.
//        items(categories) { category ->
//            // **Column** to arrange the category image and name vertically.
//            Column(
//                // Aligns children horizontally in the center.
//                horizontalAlignment = Alignment.CenterHorizontally,
//                // Makes the column clickable.
//                modifier = Modifier.clickable(
//                    onClick = { /* TODO: filter by category */ },
//                    indication = null,
//                    interactionSource = remember { MutableInteractionSource() }
//                )
//            ) {
//                // **Card** for the category image.
//                Card(
//                    shape = RoundedCornerShape(16.dp),
//                    colors = CardDefaults.cardColors(containerColor = Color(33, 33, 33)),
//                    modifier = Modifier.size(80.dp)
//                ) {
//                    // **Image** composable for the category image.
//                    Image(
//                        painter = painterResource(id = category.imageResId),
//                        contentDescription = category.name,
//                        contentScale = ContentScale.Crop,
//                        modifier = Modifier.fillMaxSize()
//                    )
//                }
//                // **Spacer** adds vertical space.
//                Spacer(Modifier.height(8.dp))
//                // **Text** for the category name.
//                Text(
//                    category.name,
//                    style = MaterialTheme.typography.bodySmall,
//                    color = Color.White,
//                    fontWeight = FontWeight.Medium
//                )
//            }
//        }
//    }
//}
//
//// Declares a composable function for displaying the hero section.
//@Composable
//fun HeroSection(recipe: Recipe, modifier: Modifier = Modifier) {
//    // **Box** composable to stack elements.
//    Box(
//        modifier = modifier
//            // Clips the content to a rounded corner shape.
//            .clip(RoundedCornerShape(20.dp))
//            // Makes the box clickable.
//            .clickable(
//                onClick = { /* TODO: Navigate to recipe detail */ },
//                indication = null,
//                interactionSource = remember { MutableInteractionSource() }
//            )
//    ) {
//        // **Image** composable to display the recipe image.
//        Image(
//            // Specifies the image resource.
//            painter = painterResource(id = recipe.imageResId),
//            // Accessibility description.
//            contentDescription = recipe.title,
//            // Scales the content to fill the bounds while maintaining aspect ratio.
//            contentScale = ContentScale.Crop,
//            // Makes the image fill the size of its parent Box.
//            modifier = Modifier.matchParentSize()
//        )
//        // Comment for the gradient overlay.
//        // Gradient overlay
//        // Another **Box** for the gradient overlay.
//        Box(
//            modifier = Modifier
//                // Makes the box fill the size of its parent Box.
//                .matchParentSize()
//                // Applies a vertical gradient background.
//                .background(
//                    Brush.verticalGradient(
//                        colors = listOf(
//                            // Top color (black with 60% opacity).
//                            Color.Black.copy(alpha = 0.6f),
//                            // Bottom color (transparent).
//                            Color.Transparent
//                        ),
//                        // Start position of the gradient.
//                        startY = 0f,
//                        // End position of the gradient.
//                        endY = 400f
//                    )
//                )
//        )
//        // **Column** to arrange text and stars vertically.
//        Column(
//            modifier = Modifier
//                // Adds padding to the column.
//                .padding(16.dp)
//                // Aligns the column to the top-start of the parent Box.
//                .align(Alignment.TopStart)
//        ) {
//            // **Text** for "RECIPE OF THE DAY".
//            Text(
//                "RECIPE OF THE DAY",
//                style = MaterialTheme.typography.labelMedium,
//                color = Color.White
//            )
//            // **Spacer** adds vertical space.
//            Spacer(Modifier.height(4.dp))
//            // **Text** for the recipe title.
//            Text(
//                recipe.title,
//                style = MaterialTheme.typography.titleLarge,
//                color = Color.White,
//                fontWeight = FontWeight.Bold
//            )
//            // **Spacer** adds vertical space.
//            Spacer(Modifier.height(8.dp))
//            // **Row** to display star ratings.
//            Row {
//                // Loops to display filled stars based on the recipe's rating.
//                repeat(recipe.rating) {
//                    // **Icon** for a filled star.
//                    Icon(
//                        Icons.Filled.Star,
//                        contentDescription = null,
//                        tint = Color(0xFFF0A800),
//                        modifier = Modifier.size(18.dp)
//                    )
//                }
//                // Loops to display outlined stars for the remaining stars (5 - rating).
//                repeat(5 - recipe.rating) {
//                    // **Icon** for an outlined star.
//                    Icon(
//                        Icons.Outlined.Star,
//                        contentDescription = null,
//                        tint = Color.Gray,
//                        modifier = Modifier.size(18.dp)
//                    )
//                }
//            }
//        }
//    }
//}
//
//// Marks this composable function as using experimental Material 3 APIs.
//@OptIn(ExperimentalMaterial3Api::class)
//// Declares a composable function for the random recipe card.
//@Composable
//fun RandomRecipeCard(modifier: Modifier = Modifier) {
//    // **Card** composable provides a Material Design card.
//    Card(
//        modifier = modifier,
//        // Sets the shape of the card to rounded corners.
//        shape = RoundedCornerShape(16.dp),
//        // Defines the colors for the card.
//        colors = CardDefaults.cardColors(containerColor = Color(40, 40, 40)),
//        // Sets the elevation of the card (how high it appears).
//        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
//    ) {
//        // **Row** to arrange content horizontally within the card.
//        Row(
//            modifier = Modifier
//                // Makes the row fill the entire size of the card.
//                .fillMaxSize()
//                // Makes the row clickable.
//                .clickable(
//                    onClick = { /* TODO: Navigate to random recipe */ },
//                    indication = null,
//                    interactionSource = remember { MutableInteractionSource() }
//                )
//                // Adds padding to the content within the row.
//                .padding(horizontal = 16.dp, vertical = 12.dp),
//            // Aligns children vertically in the center.
//            verticalAlignment = Alignment.CenterVertically,
//        ) {
//            // **Box** to display the dice icon with a background.
//            Box(
//                modifier = Modifier
//                    // Sets the size of the box.
//                    .size(48.dp)
//                    // Clips the box to a rounded corner shape.
//                    .clip(RoundedCornerShape(12.dp))
//                    // Sets the background color with some transparency.
//                    .background(Color(255, 255, 255, 200)),
//                // Aligns content in the center of the box.
//                contentAlignment = Alignment.Center
//            ) {
//                // **Image** composable for the dice icon.
//                Image(
//                    painter = painterResource(id = R.drawable.dice),
//                    contentDescription = "Random recipe icon",
//                    modifier = Modifier.size(30.dp)
//                )
//            }
//
//            // **Spacer** adds horizontal space.
//            Spacer(Modifier.width(16.dp))
//            // **Column** to arrange text vertically.
//            Column(modifier = Modifier.weight(1f)) {
//                // **Text** for "Random recipe".
//                Text(
//                    "Random recipe",
//                    style = MaterialTheme.typography.titleMedium,
//                    fontWeight = FontWeight.SemiBold,
//                    color = Color.White
//                )
//                // **Text** for the description.
//                Text(
//                    "Take a chance on your next meal!",
//                    style = MaterialTheme.typography.bodySmall,
//                    color = Color.LightGray
//                )
//            }
//            // **Icon** for the right arrow.
//            Icon(
//                Icons.AutoMirrored.Filled.KeyboardArrowRight,
//                contentDescription = "Go to random recipe",
//                tint = Color(0xFF6A54DC)
//            )
//        }
//    }
//}
//
//// Declares a composable function for displaying extra content, specifically popular recipes.
//@Composable
//fun ExtraContent(popularRecipes: List<Recipe>) {
//    // **Column** to arrange content vertically.
//    Column {
//        // **Text** for "Popular Recipes" title.
//        Text(
//            "Popular Recipes",
//            style = MaterialTheme.typography.titleMedium,
//            color = Color.White,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.padding(horizontal = 20.dp) // Consistent padding
//        )
//        // **Spacer** adds vertical space.
//        Spacer(Modifier.height(16.dp))
//
//        // Loops through each popular recipe to display its card.
//        popularRecipes.forEach { recipe ->
//            // **Card** for each popular recipe.
//            Card(
//                modifier = Modifier
//                    // Fills the maximum available width.
//                    .fillMaxWidth()
//                    // Adds horizontal padding.
//                    .padding(horizontal = 20.dp) // Consistent padding
//                    // Sets a fixed height.
//                    .height(120.dp)
//                    // Adds vertical padding.
//                    .padding(vertical = 8.dp)
//                    // Makes the card clickable.
//                    .clickable(
//                        onClick = { /* TODO: Navigate to recipe detail */ },
//                        indication = null,
//                        interactionSource = remember { MutableInteractionSource() }
//                    ),
//                shape = RoundedCornerShape(16.dp),
//                colors = CardDefaults.cardColors(containerColor = Color(33, 33, 33))
//            ) {
//                // **Row** to arrange content horizontally within the card.
//                Row(
//                    modifier = Modifier
//                        // Fills the entire size of the card.
//                        .fillMaxSize()
//                        // Adds padding to the content.
//                        .padding(16.dp),
//                    // Aligns children vertically in the center.
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    // **Box** for the recipe image.
//                    Box(
//                        modifier = Modifier
//                            // Sets the size of the box.
//                            .size(80.dp)
//                            // Clips the box to a rounded corner shape.
//                            .clip(RoundedCornerShape(12.dp))
//                            // Sets the background color.
//                            .background(Color(0xFF6A54DC)),
//                        // Aligns content in the center.
//                        contentAlignment = Alignment.Center
//                    ) {
//                        // **Image** composable for the recipe image.
//                        Image(
//                            painter = painterResource(id = recipe.imageResId),
//                            contentDescription = recipe.title,
//                            contentScale = ContentScale.Crop,
//                            modifier = Modifier.matchParentSize()
//                        )
//                    }
//                    // **Spacer** adds horizontal space.
//                    Spacer(Modifier.width(16.dp))
//                    // **Column** to arrange recipe title, description, and stars vertically.
//                    Column(modifier = Modifier.weight(1f)) {
//                        // **Text** for the recipe title.
//                        Text(
//                            text = recipe.title,
//                            style = MaterialTheme.typography.titleMedium,
//                            color = Color.White,
//                            fontWeight = FontWeight.Bold
//                        )
//                        // **Text** for the recipe description.
//                        Text(
//                            text = recipe.description,
//                            style = MaterialTheme.typography.bodySmall,
//                            color = Color.Gray,
//                            // Limits the number of lines for the description.
//                            maxLines = 2,
//                            // Adds ellipsis if the text overflows.
//                            overflow = TextOverflow.Ellipsis
//                        )
//                        // **Spacer** adds vertical space.
//                        Spacer(Modifier.height(4.dp))
//                        // Comment for dynamic star rating.
//                        // Dynamic Star Rating
//                        // **Row** to display star ratings.
//                        Row {
//                            // Loops to display filled stars based on the recipe's rating.
//                            repeat(recipe.rating) {
//                                // **Icon** for a filled star.
//                                Icon(
//                                    Icons.Filled.Star,
//                                    contentDescription = null,
//                                    tint = Color(0xFFF0A800),
//                                    modifier = Modifier.size(14.dp)
//                                )
//                            }
//                            // Loops to display outlined stars for the remaining stars.
//                            repeat(5 - recipe.rating) {
//                                // **Icon** for an outlined star.
//                                Icon(
//                                    Icons.Outlined.Star,
//                                    contentDescription = null,
//                                    tint = Color.Gray,
//                                    modifier = Modifier.size(14.dp)
//                                )
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//// Declares a composable function for the category section.
//@Composable
//fun CategorySection() {
//    // **Column** to arrange content vertically.
//    Column(modifier = Modifier.padding(top = 8.dp)) {
//        // **Row** to arrange "Categories" title and "See all" text horizontally.
//        Row(
//            modifier = Modifier
//                // Makes the row fill the maximum available width.
//                .fillMaxWidth()
//                // Adds horizontal padding.
//                .padding(horizontal = 20.dp), // Consistent padding
//            // Arranges children horizontally with space between them.
//            horizontalArrangement = Arrangement.SpaceBetween,
//            // Aligns children vertically in the center.
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            // **Text** for "Categories" title.
//            Text(
//                "Categories",
//                style = MaterialTheme.typography.titleLarge,
//                color = Color.White,
//                fontWeight = FontWeight.Bold
//            )
//            // **Text** for "See all".
//            Text(
//                "See all",
//                color = Color(0xFF6A54DC),
//                style = MaterialTheme.typography.bodyLarge,
//                modifier = Modifier.clickable(
//                    onClick = { /* TODO: see all */ },
//                    indication = null,
//                    interactionSource = remember { MutableInteractionSource() }
//                )
//            )
//        }
//        // **Spacer** adds vertical space.
//        Spacer(Modifier.height(16.dp))
//        // Displays the **CategoryRow** with the list of categories.
//        CategoryRow(categories = categories)
//    }
//}
//
//// Marks this composable function as using experimental Material 3 APIs.
//@OptIn(ExperimentalMaterial3Api::class)
//// Declares a composable function for the application's top app bar.
//@Composable
//fun AppTopBar(onSearchClick: () -> Unit) { // Added a lambda parameter for search icon click
//    // Defines the background color for the top bar.
//    val backgroundColor = Color(22, 22, 22)
//
//    // **TopAppBar** is a Material 3 composable for displaying a top application bar.
//    TopAppBar(
//        // Defines the title content of the top app bar.
//        title = {
//            // **Row** arranges its children horizontally.
//            Row(
//                // Modifiers apply layout and appearance changes to the composable.
//                modifier = Modifier
//                    // Makes the row fill the maximum available width.
//                    .fillMaxWidth()
//                    // Adds horizontal padding.
//                    //.padding(horizontal = 20.dp), // Consistent padding
//                    .padding(start = 6.dp, end = 20.dp)
//                ,verticalAlignment = Alignment.CenterVertically,
//                // Arranges children horizontally with space between them.
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                // Comment for the left section of the top bar (Avatar + Name).
//                // Left: Avatar + Name
//                // Another **Row** for the avatar and name.
//                Row(
//                    // Aligns children vertically in the center.
//                    verticalAlignment = Alignment.CenterVertically,
//                    // Makes the row clickable.
//                    modifier = Modifier.clickable(
//                        // Defines the action when clicked (placeholder for navigation).
//                        onClick = { /* TODO: Navigate to profile */ },
//                        // Disables the visual ripple effect on click.
//                        indication = null,
//                        // Provides an interaction source for tracking press states, etc.
//                        interactionSource = remember { MutableInteractionSource() }
//                    )
//                ) {
//                    // **Image** composable to display the user's avatar.
//                    Image(
//                        // Specifies the image resource from drawables.
//                        painter = painterResource(id = R.drawable.daniel),
//                        // Accessibility description for the image.
//                        contentDescription = "User avatar",
//                        modifier = Modifier
//                            // Sets the size of the image.
//                            .size(35.dp)
//                            // Clips the image to a circular shape.
//                            .clip(CircleShape),
//                        // Scales the content to fill the bounds while maintaining aspect ratio.
//                        contentScale = ContentScale.Crop
//                    )
//                    // **Spacer** adds empty space horizontally.
//                    Spacer(Modifier.width(8.dp))
//                    // **Text** composable for the user's name.
//                    Text(
//                        "Daniel",
//                        // Applies text style from the Material Theme typography.
//                        style = MaterialTheme.typography.titleSmall,
//                        // Sets the text color to white.
//                        color = Color.White
//                    )
//                    // **Icon** composable for an arrow icon.
//                    Icon(
//                        // Specifies the icon vector graphic.
//                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
//                        // Accessibility description for the icon.
//                        contentDescription = "Go to profile",
//                        // Sets the icon color to white.
//                        tint = Color.White,
//                        modifier = Modifier
//                            // Sets the size of the icon.
//                            .size(20.dp)
//                            // Adds start padding to the icon.
//                            .padding(start = 4.dp) // Add some space between name and icon
//                    )
//                }
//                // Comment for the right section of the top bar (Search + Coins).
//                // Right: Search + Coins (adjusted sizes to match left side)
//                // Another **Row** for the search icon and coin display.
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    // **Icon** composable for a search icon.
//                    Icon(
//                        Icons.Filled.Search,
//                        contentDescription = "Search",
//                        tint = Color.White,
//                        modifier = Modifier
//                            // Sets the size of the icon.
//                            .size(18.dp) // Increased from 20dp to match left side proportions
//                            // Makes the icon clickable and calls the onSearchClick lambda.
//                            .clickable(
//                                onClick = onSearchClick, // Call the lambda here
//                                indication = null,
//                                interactionSource = remember { MutableInteractionSource() }
//                            )
//                    )
//                    // **Spacer** adds horizontal space.
//                    Spacer(Modifier.width(12.dp))
//                    // **Box** composable to display the coin count.
//                    Box(
//                        modifier = Modifier
//                            // Sets the background color with a rounded rectangular shape.
//                            .background(
//                                color = Color(0xFF6A54DC),
//                                shape = RoundedCornerShape(8.dp) // Slightly increased corner radius
//                            )
//                            // Adds horizontal and vertical padding.
//                            .padding(horizontal = 10.dp, vertical = 5.dp) // Increased padding to match left side height
//                            // Makes the box clickable.
//                            .clickable(
//                                onClick = { /* TODO: Open coins */ },
//                                indication = null,
//                                interactionSource = remember { MutableInteractionSource() }
//                            ),
//                        // Aligns content in the center of the box.
//                        contentAlignment = Alignment.Center
//                    ) {
//                        // **Text** composable for the coin count.
//                        Text(
//                            text = "458",
//                            style = MaterialTheme.typography.bodySmall,
//                            color = Color.White,
//                            fontWeight = FontWeight.Bold
//                        )
//                    }
//                }
//            }
//        },
//        // Defines the colors for different parts of the **TopAppBar**.
//        colors = TopAppBarDefaults.topAppBarColors(
//            // Background color of the top app bar.
//            containerColor = backgroundColor,
//            // Color of the title content.
//            titleContentColor = Color.White,
//            // Color of action icons.
//            actionIconContentColor = Color.White,
//            // Color of the navigation icon.
//            navigationIconContentColor = Color.White
//        ),
//        // Modifies the top app bar, removing any top padding.
//        modifier = Modifier.padding(top = 0.dp) // Remove any top padding
//    )
//}
//
//// Declares a composable function for the home screen.
//@Composable
//fun HomeScreen() {
//    // Remembers the scroll state to enable scrolling on the column.
//    val scrollState = rememberScrollState()
//    // State to control the visibility of the search modal.
//    var showSearchModal by remember { mutableStateOf(false) } // State for modal visibility
//
//    // Finds the recipe marked as "Recipe of the Day" from allRecipes.
//    val recipeOfTheDay = allRecipes.find { it.isRecipeOfTheDay }
//    // Filters allRecipes to get popular recipes (those not marked as "Recipe of the Day").
//    val popularRecipes = allRecipes.filter { !it.isRecipeOfTheDay }
//
//    // **Column** arranges its children vertically.
//    Column(
//        modifier = Modifier
//            // Makes the column fill the entire available size.
//            .fillMaxSize()
//            // Sets the background color.
//            .background(Color(22, 22, 22))
//            // Adds padding equal to the status bar height to avoid content overlapping.
//            .statusBarsPadding()
//            // Enables vertical scrolling for the column.
//            .verticalScroll(
//                state = scrollState,
//                enabled = true
//            ),
//        // Aligns children horizontally in the center.
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        // Comment indicating the removal of extra spacing.
//        // Remove extra spacing above the top bar
//        // Displays the custom **AppTopBar**, passing a lambda to show the modal.
//        AppTopBar(onSearchClick = { showSearchModal = true }) // Pass the lambda here
//
//        recipeOfTheDay?.let { recipe ->
//            // Displays the **HeroSection** with the Recipe of the Day.
//            HeroSection(
//                recipe = recipe,
//                modifier = Modifier
//                    // Fills the maximum available width.
//                    .fillMaxWidth()
//                    // Adds horizontal padding.
//                    .padding(horizontal = 20.dp)
//                    // Sets the aspect ratio of the composable.
//                    .aspectRatio(1.2f)
//                    // Adds top padding.
//                    .padding(top = 8.dp) // Reduced from 16dp
//            )
//        }
//
//        // **Spacer** adds vertical empty space.
//        Spacer(Modifier.height(24.dp))
//
//        // Displays the **RandomRecipeCard**.
//        RandomRecipeCard(
//            modifier = Modifier
//                // Fills the maximum available width.
//                .fillMaxWidth()
//                // Adds horizontal padding.
//                .padding(horizontal = 20.dp)
//                // Sets a fixed height.
//                .height(70.dp)
//        )
//
//        // **Spacer** adds vertical empty space.
//        Spacer(Modifier.height(32.dp))
//        // Displays the **CategorySection**.
//        CategorySection()
//        // **Spacer** adds vertical empty space.
//        Spacer(Modifier.height(32.dp))
//        // Displays the **ExtraContent** section with popular recipes.
//        ExtraContent(popularRecipes)
//        // **Spacer** adds vertical empty space.
//        Spacer(Modifier.height(16.dp))
//    }
//
//    // Display the SearchModal if showSearchModal is true
//    if (showSearchModal) {
//        SearchModal(onDismiss = { showSearchModal = false })
//    }
//}
//
//// Marks this composable function as using experimental Material 3 APIs.
//@OptIn(ExperimentalMaterial3Api::class)
//// Declares a composable function for the bottom navigation bar.
//@Composable
//fun BottomNavBar(navController: NavHostController) {
//    // List of navigation routes/items.
//    val items = listOf("home", "saved", "cart", "chat", "profile")
//    // Observes the current back stack entry to get the current route for highlighting the selected item.
//    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
//    // Defines the color for the top border of the navigation bar.
//    val topBorderColor = Color.White.copy(alpha = 0.15f)
//    // Defines the corner radius for the navigation bar's shape.
//    val cornerRadius = 16.dp
//    // Defines the rounded corner shape for the top-start and top-end of the navigation bar.
//    val navBarShape = RoundedCornerShape(topStart = cornerRadius, topEnd = cornerRadius)
//
//    // The main container for the Navbar, clipped to the desired shape
//    // **Column** to contain the NavigationBar and apply shaping/border.
//    Column(
//        modifier = Modifier
//            // Makes the column fill the maximum available width.
//            .fillMaxWidth()
//            // Clips the column's content to the defined rounded shape.
//            .clip(navBarShape) // Apply the rounded shape to the whole Column
//            // Sets the background color of the column.
//            .background(Color(22, 22, 22)) // Apply background to the Column to fill the rounded shape
//            // Adds a border with the specified width, color, and shape.
//            .border(
//                width = 1.dp, // Border thickness
//                color = topBorderColor, // Border color
//                shape = navBarShape // Apply the same rounded shape to the border
//            )
//    ) {
//        // Comment indicating border handling.
//        // Remove the separate Box for the border as it's now handled by the .border modifier
//
//        // **CompositionLocalProvider** is used to override local composition values for its content.
//        // Here, it disables the ripple effect for clickable items within the navigation bar.
//        CompositionLocalProvider(LocalRippleConfiguration provides null) {
//            // **NavigationBar** is a Material 3 composable for bottom navigation.
//            NavigationBar(
//                modifier = Modifier
//                    // Ensures it fills the width of the parent column.
//                    .fillMaxWidth() // Ensure it fills the width of the parent Column
//                    // Sets a fixed height for the navigation bar.
//                    .height(75.dp)
//                //.padding(top = 20.dp),
//                ,containerColor = Color.Transparent, // Makes the NavigationBar transparent to show the Column's background.
//                // Sets the tonal elevation (shadow) to 0dp, making it flat.
//                tonalElevation = 0.dp,
//            ) {
//                // Iterates through each route (item) in the navigation bar.
//                items.forEach { route ->
//                    // Checks if the current route matches the item's route to determine if it's selected.
//                    val isSelected = currentRoute == route
//
//                    // Determines the icon asset (filled or outlined) based on selection and route.
//                    val iconAsset = when (route) {
//                        "home" -> if (isSelected) Icons.Filled.Home else Icons.Outlined.Home
//                        "saved" -> if (isSelected) painterResource(id = R.drawable.bookmark_filled) else painterResource(id = R.drawable.bookmark_hollow)
//                        "chat" -> if (isSelected) painterResource(id = R.drawable.chat_filled) else painterResource(id = R.drawable.chat_hollow)
//                        "cart" -> if (isSelected) Icons.Filled.ShoppingCart else Icons.Outlined.ShoppingCart
//                        else -> if (isSelected) Icons.Filled.Person else Icons.Outlined.Person
//                    }
//
//                    NavigationBarItem(
//                        selected = isSelected, // Sets whether this item is currently selected.
//                        onClick = {
//                            // If the item is not already selected, navigate to its route.
//                            if (!isSelected) navController.navigate(route) {
//                                // Prevents multiple copies of the same destination from being added to the back stack.
//                                launchSingleTop = true
//                                // Restores the state of the destination if it was previously on the back stack.
//                                restoreState = true
//                            }
//                        },
//                        icon = {
//                            // Defines common modifiers for the icon.
//                            val iconModifier = Modifier
//                                .size(22.dp)
//                                .align(Alignment.CenterVertically) // ✅ Center the icon vertically
//                            // Defines the color of the icon based on selection.
//                            val iconColor = if (isSelected) Color(0xFF6A54DC) else Color.Gray
//
//                            // Special handling for the "cart" item to display a badge.
//                            if (route == "cart") {
//                                // **BadgedBox** displays a badge over its content.
//                                BadgedBox(
//                                    badge = { Badge { Text("3") } }, // Displays a badge with "3".
//                                    modifier = Modifier.align(Alignment.CenterVertically) // ✅ Align the badge+icon combo
//                                ) {
//                                    // Checks if the icon asset is a **Painter** (for custom drawables) or **ImageVector** (for Material Icons).
//                                    if (iconAsset is Painter) {
//                                        Icon(painter = iconAsset, contentDescription = route, tint = iconColor, modifier = iconModifier)
//                                    } else {
//                                        Icon(imageVector = iconAsset as ImageVector, contentDescription = route, tint = iconColor, modifier = iconModifier)
//                                    }
//                                }
//                            } else {
//                                // Regular icon display for other items without a badge.
//                                if (iconAsset is Painter) {
//                                    Icon(painter = iconAsset, contentDescription = route, tint = iconColor, modifier = iconModifier)
//                                } else {
//                                    Icon(imageVector = iconAsset as ImageVector, contentDescription = route, tint = iconColor, modifier = iconModifier)
//                                }
//                            }
//                        },
//                        label = null, // No text label below the icon.
//                        alwaysShowLabel = false, // Ensures the label is not shown even if it existed.
//                        colors = NavigationBarItemDefaults.colors(
//                            selectedIconColor = Color(0xFF6A54DC), // Color of the icon when selected.
//                            unselectedIconColor = Color.Gray, // Color of the icon when not selected.
//                            indicatorColor = Color.Transparent // Makes the background indicator transparent.
//                        )
//                    )
//                }
//            }
//        }
//    }
//}
//
////---
////## Modified Search Modal
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SearchModal(onDismiss: () -> Unit) {
//    var searchText by remember { mutableStateOf("") }
//    val baseBackground = Color(22, 22, 22).copy(alpha = 0.6f)
//    val highlightBackground = Color(40, 40, 40).copy(alpha = 0.6f)
//
//    Dialog(
//        onDismissRequest = onDismiss,
//        properties = DialogProperties(usePlatformDefaultWidth = false)
//    ) {
//        // Root container: full screen, content aligned to top center
//        Box(
//            modifier = Modifier
//                .fillMaxSize(),
//            contentAlignment = Alignment.TopCenter
//        ) {
//            // Modal card: fixed size, clipped, blurred background only
//            Box(
//                modifier = Modifier
//                    .padding(horizontal = 20.dp, vertical = 40.dp)
//                    .fillMaxWidth()
//                    .height(280.dp)
//                    .clip(RoundedCornerShape(20.dp))
//            ) {
//                // Background layer with blur
//                Box(
//                    modifier = Modifier
//                        .matchParentSize()
//                        .background(
//                            Brush.verticalGradient(
//                                colors = listOf(highlightBackground, baseBackground)
//                            )
//                        )
//                        .border(1.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(20.dp))
//                        .blur(12.dp)
//                )
//                // Foreground content: TextField only, not blurred
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(16.dp),
//                    verticalArrangement = Arrangement.Top
//                ) {
//                    TextField(
//                        value = searchText,
//                        onValueChange = { searchText = it },
//                        placeholder = { Text("Search recipes...", color = Color.Gray) },
//                        singleLine = true,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(56.dp)
//                            .clip(RoundedCornerShape(12.dp)),
//                        colors = TextFieldDefaults.colors(
//                            focusedContainerColor = Color.Transparent,
//                            unfocusedContainerColor = Color.Transparent,
//                            focusedTextColor = Color.White,
//                            unfocusedTextColor = Color.White,
//                            cursorColor = Color.White,
//                            focusedIndicatorColor = Color.Transparent,
//                            unfocusedIndicatorColor = Color.Transparent
//                        )
//                    )
//                }
//            }
//        }
//    }
//}
//
////---
//// Main Application Structure
//
//// Marks this composable function as using experimental Material 3 APIs.
//@OptIn(ExperimentalMaterial3Api::class)
//// Declares a composable function, which is the building block of Compose UI.
//@Composable
//fun RecipeManagerApp() {
//    // Defines a Color object for the background, using RGB values.
//    val backgroundColor = Color(22, 22, 22) //
//    // Creates and remembers a **NavHostController**, which manages navigation between screens.
//    val navController = rememberNavController() //
//
//    // **Scaffold** provides a basic layout structure for Material Design components (like top bar, bottom bar, etc.).
//    Scaffold(
//        // Sets the background color of the scaffold.
//        containerColor = backgroundColor, //
//        // Defines the content for the bottom navigation bar.
//        bottomBar = { BottomNavBar(navController) } //
//    ) { innerPadding ->
//        // **Box** is a layout composable that stacks its children on top of each other.
//        Box(modifier = Modifier.padding(innerPadding)) { //
//            // **NavHost** is a composable that hosts a navigation graph, allowing navigation between destinations.
//            NavHost(navController, startDestination = "home") { //
//                // Defines a composable destination for the "home" route.
//                composable("home") { HomeScreen() } //
//                // Comment indicating where more screens would be added.
//                // Further screens can be added here
//            }
//        }
//    }
//}
//
//
//// The main activity of the Android application.
//class MainActivity : ComponentActivity() {
//    // Called when the activity is first created.
//    override fun onCreate(savedInstanceState: Bundle?) {
//        // Calls the superclass's onCreate method.
//        super.onCreate(savedInstanceState) //
//        // Enables edge-to-edge display, allowing content to extend under system bars.
//        enableEdgeToEdge() //
//        // Sets the Compose UI content for this activity.
//        setContent { //
////            // Applies the custom theme defined in **RecipeManagerAppTheme**.
////            RecipeManagerAppTheme { //
////                // Calls the main composable function for the application.
////                RecipeManagerApp() //
////            }
//            Profile()
//        }
//    }
//}
//
//// Declares a preview composable function.
//@Preview(showBackground = true) // Shows the background in the preview.
//@Composable
//fun DefaultPreview() {
//    // Applies the theme for the preview.
//    RecipeManagerAppTheme { //
//        // Displays the main application composable in the preview.
//        RecipeManagerApp() //
//    }
//}