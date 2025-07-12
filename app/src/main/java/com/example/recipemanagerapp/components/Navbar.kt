package com.example.recipemanagerapp.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.recipemanagerapp.NavigationItem


/**
 * The updated navigation bar that handles navigation and icon state changes.
 * @param navController The controller to manage navigation.
 * @param currentRoute The currently active navigation route.
 */
@Composable
fun CustomNavBar(navController: NavController, currentRoute: String?) {
    val navBarShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    val darkBackground = Color(22, 22, 22)

    val navItems = listOf(
        NavigationItem("home", Icons.Filled.Home, Icons.Outlined.Home, "Home"),
        NavigationItem("favorites", Icons.Filled.Favorite, Icons.Outlined.FavoriteBorder, "Favorites"),
        NavigationItem("notifications", Icons.Filled.Notifications, Icons.Outlined.Notifications, "Notifications"),
    )

    val indicatorOffset = remember { mutableStateOf(0.dp) }
    val indicatorWidth = remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current

    val animatedOffset by animateDpAsState(
        targetValue = indicatorOffset.value,
        animationSpec = tween(durationMillis = 300)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = darkBackground, shape = navBarShape)
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.15f),
                shape = navBarShape
            )
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .offset(x = animatedOffset, y = (-8).dp)
                .width(indicatorWidth.value / 2)
                .height(2.dp)
                .align(Alignment.TopStart)
                .background(
                    Color(0xFF6A54DC),
                    RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp, bottomStart = 0.dp, bottomEnd = 0.dp)
                )
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            navItems.forEach { item ->
                val isSelected = currentRoute == item.route
                val icon = if (isSelected) item.selectedIcon else item.unselectedIcon
                val tint = if (isSelected) Color(0xFF6A54DC) else Color.Gray

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                        .onGloballyPositioned { coordinates ->
                            if (isSelected) {
                                val itemWidth = with(density) { coordinates.size.width.toDp() }
                                indicatorWidth.value = itemWidth

                                val itemCenterGlobalX = with(density) { coordinates.positionInWindow().x.toDp() } + (itemWidth / 2)

                                val sliderHalfWidth = (itemWidth / 4)

                                indicatorOffset.value = itemCenterGlobalX - sliderHalfWidth - 16.dp
                            }
                        }
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            if (!isSelected) {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                        .padding(vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = item.contentDescription,
                        modifier = Modifier.size(24.dp),
                        tint = tint
                    )
                }
            }
        }
    }
}
