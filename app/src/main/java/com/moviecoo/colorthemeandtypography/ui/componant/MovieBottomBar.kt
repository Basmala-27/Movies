package com.moviecoo.colorthemeandtypography.ui.componant

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.moviecoo.colorthemeandtypography.ui.theme.OrangeAccent


@Composable
fun MovieBottomBar() {
    NavigationBar(
        containerColor = Color.Black
    ) {

        NavigationBarItem(
            selected = true,
            onClick = {  },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home", tint = OrangeAccent) },
            label = { Text("Home", color = OrangeAccent) },
            colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent)
        )

        NavigationBarItem(
            selected = false,
            onClick = {  },
            icon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.LightGray) },
            label = { Text("Search", color = Color.LightGray) }
        )

        NavigationBarItem(
            selected = false,
            onClick = { /* Navigate Watchlist */ },
            icon = { Icon(Icons.Default.Search, contentDescription = "Watchlist", tint = Color.LightGray) },
            label = { Text("Watchlist", color = Color.LightGray) }
        )

        NavigationBarItem(
            selected = false,
            onClick = { /* Navigate Profile */ },
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile", tint = Color.LightGray) },
            label = { Text("Profile", color = Color.LightGray) }
        )
    }
}
