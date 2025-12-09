package com.moviecoo.colorthemeandtypography.common_components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.material.icons.outlined.ChatBubbleOutline

// ---------------------------------------------
/**
 * Data class representing a single item in the custom bottom navigation bar.
 *
 * @param label The text label for the item.
 * @param icon The [ImageVector] for the item's icon.
 * @param activeColor The primary color used when the item is selected.
 * @param badgeCount The optional number to display in a badge (e.g., notification count).
 */
data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val activeColor: Color,
    val badgeCount: Int? = null
)
// ---------------------------------------------

/**
 * ## AnimatedBottomBar
 * A custom, animated bottom navigation bar implementation with a "glassmorphism" style.
 *
 * This component displays a list of navigation items and uses state to track the
 * currently selected item, delegating click events to the parent.
 *
 * @param modifier The modifier for this composable.
 * @param feedItemCount The badge count specifically for the "Feed" item.
 * @param selectedIndex The index of the currently selected item.
 * @param onItemSelected Lambda function invoked when an item is clicked, providing the index.
 */
@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun AnimatedBottomBar(
    modifier: Modifier = Modifier,
    feedItemCount: Int,
    selectedIndex: Int,
    onItemSelected: (index: Int) -> Unit
) {
    // Defines the full list of navigation items and their properties.
    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home, Color(0xFF428EC2)),
        BottomNavItem("Chat", Icons.Outlined.ChatBubbleOutline, Color(0xFF5CDAB6)),
        // The badgeCount parameter for 'Feed' is dynamically set by feedItemCount.
        BottomNavItem(
            "Feed",
            Icons.Default.Favorite,
            Color(0xFFFA1E1E),
            badgeCount = feedItemCount
        ),
        BottomNavItem("Saved", Icons.Outlined.BookmarkBorder, Color(0xFFB34CC5)),
        BottomNavItem("Profile", Icons.Default.AccountCircle, Color(0xFF8263EA))
    )

    // Surface creates the main container for the bottom bar, applying visual styling.
    androidx.compose.material3.Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(75.dp)
            // Note: The blur effect requires API 31 (S) or higher to function properly.
            .blur(radius = 0.dp, edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(28.dp))),
        // Glassmorphism effect: Semi-transparent background with white boundary.
        color = Color.White.copy(alpha = 0.2f),
        shape = RoundedCornerShape(28.dp),
        shadowElevation = 12.dp,
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.3f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Iterate over the items to create individual bar items.
            items.forEachIndexed { index, item ->
                AnimatedBottomBarItem(
                    item = item,
                    isSelected = (index == selectedIndex),
                    onClick = { onItemSelected(index) }
                )
            }
        }
    }
}

// -----------------------------------------------------

/**
 * ## AnimatedBottomBarItem
 * A single, interactive item within the [AnimatedBottomBar].
 *
 * It uses color and size animations to smoothly transition between selected and unselected states.
 *
 * @param item The data object containing the item's details.
 * @param isSelected Boolean flag indicating if this item is currently selected.
 * @param onClick Lambda function executed when the item is clicked.
 */
@Composable
fun AnimatedBottomBarItem(
    item: BottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    // Smoothly animate the content color (icon/text) between active and default.
    val contentColor by animateColorAsState(
        targetValue = if (isSelected) item.activeColor else Color.Black.copy(alpha = 0.7f),
        animationSpec = tween(durationMillis = 300),
        label = "Content Color Animation"
    )

    // Smoothly animate the background color for the pill-shape effect.
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) item.activeColor.copy(alpha = 0.2f) else Color.Transparent,
        animationSpec = tween(durationMillis = 300),
        label = "Background Color Animation"
    )

    // Choose badge text color based on selection state.
    val badgeTextColor = if (isSelected) Color.Black else Color.Red

    Box(
        modifier = Modifier
            // 1. Animate Content Size: Allows the box to smoothly expand when text appears (selected state).
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessMediumLow
                )
            )
            // 2. Styling: Clip, background, and clickable behavior applied.
            .clip(RoundedCornerShape(50)) // Creates the pill shape
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        if (isSelected) {
            // SELECTED STATE: Displays both Icon and Label in a Row.
            Row(verticalAlignment = Alignment.CenterVertically) {
                BadgedBox(
                    badge = {
                        // Badge logic for the icon when selected.
                        if (item.badgeCount != null && item.badgeCount > 0) {
                            Text(
                                text = item.badgeCount.toString(),
                                color = badgeTextColor,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                // No offset needed here as the icon is centered in the Row.
                            )
                        }
                    }
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = contentColor,
                        // Apply a subtle shadow/glow effect to the icon when selected.
                        modifier = Modifier
                            .shadow(
                                elevation = 18.dp,
                                shape = CircleShape,
                                ambientColor = item.activeColor.copy(alpha = 0.8f),
                                spotColor = item.activeColor.copy(alpha = 0.8f)
                            )
                    )
                }
                Spacer(Modifier.width(8.dp))
                // The label text, which makes the item expand due to animateContentSize.
                Text(
                    text = item.label,
                    color = contentColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        } else {
            // UNSELECTED STATE: Displays only the Icon (in a Column for centering).
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                BadgedBox(
                    badge = {
                        // Badge logic for the icon when unselected.
                        if (item.badgeCount != null && item.badgeCount > 0) {
                            Text(
                                text = item.badgeCount.toString(),
                                color = badgeTextColor,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                // Uses an offset to position the badge correctly above the icon.
                                modifier = Modifier.offset(y = (-4).dp)
                            )
                        }
                    }
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = contentColor
                        // No shadow/glow applied in the unselected state.
                    )
                }

            }
        }
    }
}