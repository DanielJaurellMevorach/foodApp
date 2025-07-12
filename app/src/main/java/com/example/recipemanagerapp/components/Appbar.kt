package com.example.recipemanagerapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.recipemanagerapp.R
import com.example.recipemanagerapp.profiles


@Composable
fun CustomTopAppBar(
    modifier: Modifier = Modifier,
    onProfileClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.statusBars),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.clickable(
                onClick = onProfileClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.daniel),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Text(
                text = profiles[0].name,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Arrow Icon to profile settings",
                tint = Color.White
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),

            ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon",
                modifier = Modifier.size(20.dp),
                tint = Color.White
            )
            Box(
                modifier = Modifier.background(
                    color = Color(0xFF6A54DC),
                    shape = RoundedCornerShape(8.dp)
                )
            ) {
                Text(
                    color = Color.White,
                    text = profiles[0].points.toString(),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 0.dp)
                )
            }
        }
    }
}