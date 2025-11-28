package com.moviecoo.colorthemeandtypography.common_components

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.outlined.ChatBubbleOutline


// ---------------------------------------------
data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val activeColor: Color,
    val badgeCount: Int? = null
)
// ---------------------------------------------

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun AnimatedBottomBar(
    modifier: Modifier = Modifier,
    feedItemCount: Int,
    selectedIndex: Int,
    onItemSelected: (index: Int) -> Unit
) {
    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home, Color(0xFF428EC2)),
        BottomNavItem("Chat", Icons.Outlined.ChatBubbleOutline, Color(0xFF5CDAB6)),
        BottomNavItem(
            "Feed",
            Icons.Default.Favorite,
            Color(0xFFFF1E1E),
            badgeCount = feedItemCount
        ),
        BottomNavItem("Saved", Icons.Outlined.BookmarkBorder, Color(0xFFB34CC5)),
        BottomNavItem("Profile", Icons.Default.AccountCircle, Color(0xFF8263EA))
    )


    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(75.dp)
            .blur(radius = 0.dp, edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(28.dp))),
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

@Composable
fun AnimatedBottomBarItem(
    item: BottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val contentColor by animateColorAsState(
        targetValue = if (isSelected) item.activeColor else Color.Black.copy(alpha = 0.7f),
        animationSpec = tween(durationMillis = 300),
        label = "Content Color"
    )

    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) item.activeColor.copy(alpha = 0.2f) else Color.Transparent,
        animationSpec = tween(durationMillis = 300),
        label = "Background Color"
    )

    val badgeTextColor = if (isSelected) Color.Black else Color.Red

    Box(
        modifier = Modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessMediumLow
                )
            )
            .clip(RoundedCornerShape(50))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        if (isSelected) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                BadgedBox(
                    badge = {
                        if (item.badgeCount != null && item.badgeCount > 0) {
                            Text(
                                text = item.badgeCount.toString(),
                                color = badgeTextColor,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                            )
                        }
                    }
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = contentColor,
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
                Text(
                    text = item.label,
                    color = contentColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                BadgedBox(
                    badge = {
                        if (item.badgeCount != null && item.badgeCount > 0) {
                            Text(
                                text = item.badgeCount.toString(),
                                color = badgeTextColor,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.offset(y = (-4).dp)
                            )
                        }
                    }
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = contentColor
                    )
                }

            }
        }
    }
}
