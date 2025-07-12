package com.example.recipemanagerapp.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipemanagerapp.ProfileData
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePopup(
    profile: ProfileData,
    onDismiss: () -> Unit
) {
    var offsetY by remember { mutableStateOf(0f) }
    var isVisible by remember { mutableStateOf(true) }

    val density = LocalDensity.current
    val screenHeightPx = LocalDensity.current.run { 2000.dp.toPx() } // Approximate screen height
    val accentPurple = Color(0xFF6A54DC)
    val popupShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)

    // Define a smaller threshold for dismissal in pixels
    val dismissThresholdPx = with(density) { 100.dp.toPx() }

    val overlayAlpha by animateFloatAsState(
        targetValue = if (isVisible) 0.6f else 0f,
        animationSpec = tween(durationMillis = 400)
    )

    val animatedOffsetY by animateFloatAsState(
        targetValue = if (isVisible) 0f else screenHeightPx,
        animationSpec = tween(durationMillis = 400),
        finishedListener = { if (!isVisible) onDismiss() }
    )

    // Full-screen overlay
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = overlayAlpha))
            .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) { isVisible = false }
    )

    // Main popup content
    Box(
        modifier = Modifier
            .fillMaxSize()
            .offset { IntOffset(0, (animatedOffsetY + offsetY).roundToInt()) }
            .pointerInput(Unit) {
                detectVerticalDragGestures(
                    onVerticalDrag = { _, dragAmount ->
                        // Allow dragging downwards from the initial position
                        if (offsetY + dragAmount > 0) {
                            offsetY += dragAmount
                        }
                    },
                    onDragEnd = {
                        // If dragged down more than the new, smaller threshold, dismiss the popup
                        if (offsetY > dismissThresholdPx) {
                            isVisible = false
                        } else {
                            // Otherwise, snap back to the original position
                            offsetY = 0f
                        }
                    }
                )
            }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.925f)
                .align(Alignment.BottomCenter)
                .border(
                    width = 1.dp,
                    color = Color.White.copy(alpha = 0.15f),
                    shape = popupShape
                ),
            shape = popupShape,
            colors = CardDefaults.cardColors(containerColor = Color(22, 22, 22)),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp) // Remove default elevation
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Draggable handle
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(4.dp)
                        .background(accentPurple, RoundedCornerShape(2.dp))
                )
                Spacer(modifier = Modifier.height(24.dp))

                // Profile Info
                Image(
                    painter = painterResource(id = profile.profilePicture),
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        //.border(2.dp, accentPurple, CircleShape)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = profile.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${profile.points} points",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = accentPurple
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Stats Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatItem(value = profile.cooked.toString(), label = "Cooked")
                    StatItem(value = profile.contributed.toString(), label = "Contributed")
                    StatItem(value = profile.followers.toString(), label = "Followers")
                    StatItem(value = profile.likes.toString(), label = "Likes")
                }
            }
        }
    }
}

@Composable
fun StatItem(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6A54DC) // Purple accent
        )
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.White
        )
    }
}
