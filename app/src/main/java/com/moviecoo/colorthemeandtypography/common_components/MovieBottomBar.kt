package com.moviecoo.colorthemeandtypography.common_components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.ui.graphics.vector.ImageVector
import com.moviecoo.colorthemeandtypography.ui.theme.OrangeAccent
import com.moviecoo.colorthemeandtypography.ui.theme.Surface
import androidx.compose.ui.Modifier // Added Modifier import for completeness


// ---------------------------------------------
/**
 * Data class representing a single destination (item) in the bottom navigation bar.
 *
 * @param route The unique route string used by the NavController for navigation.
 * @param label The text label displayed below the icon.
 * @param icon The [ImageVector] for the navigation icon.
 */
private data class BottomNavDestination(
    val route: String,
    val label: String,
    val icon: ImageVector
)

// Defines the list of all destinations in the bottom bar.
private val destinations = listOf(
    BottomNavDestination("home_route", "Home", Icons.Default.Home),
    BottomNavDestination("search_route", "Search", Icons.Default.Search),
    BottomNavDestination("watchlist_route", "Watchlist", Icons.Default.FavoriteBorder),
    BottomNavDestination("profile_route", "Profile", Icons.Default.Person)
)
// ---------------------------------------------

/**
 * ## MovieBottomBar
 * A custom [NavigationBar] component designed for movie applications.
 *
 * This component integrates directly with the [NavController] to manage its selection state
 * and handle navigation events, following standard Jetpack Compose navigation patterns.
 *
 * @param navController The NavController that manages the navigation graph for the main screen.
 */
@Composable
fun MovieBottomBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    // 1. Determine Current Route
    // Get the current entry in the navigation back stack as a state.
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    // Extract the route string from the current destination.
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = modifier,
        containerColor = Surface , // Assumed custom color
    ) {
        // Iterate over the defined destinations to build the items.
        destinations.forEach { destination ->
            // Determine if the current item matches the currently active route.
            val isSelected = currentRoute == destination.route

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    // Navigate to the new route using the NavController.
                    navController.navigate(destination.route) {
                        // Avoid building up a large stack of destinations on the back stack.
                        // Pop up to the start destination of the graph to avoid multiple copies
                        // of the same destination when reselecting the same item.
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        // Avoid duplicates when reselecting the same item.
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item.
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = destination.label,
                        // Use the selection state to determine the icon color.
                        tint = getIconColor(isSelected)
                    )
                },
                label = {
                    Text(
                        text = destination.label,
                        // Use the selection state to determine the label color.
                        color = getIconColor(isSelected)
                    )
                },
                // Set the indicator color to transparent to remove the default colored background.
                colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent)
            )
        }
    }
}

/**
 * Helper function to determine the color based on the selection state.
 *
 * @param isSelected True if the item is currently active.
 * @return [OrangeAccent] if selected, otherwise [Color.LightGray].
 */
private fun getIconColor(isSelected: Boolean): Color {
    return if (isSelected) OrangeAccent else Color.LightGray
}