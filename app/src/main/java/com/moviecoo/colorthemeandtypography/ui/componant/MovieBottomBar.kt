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
fun MovieBottomBar(home: Boolean = false , search: Boolean =false , watchlist: Boolean = false, profile: Boolean= false) {
    NavigationBar(
        containerColor = Color.Black
    ) {

        NavigationBarItem(
            selected = home,
            onClick = {  },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home", tint = isSelected(home) ) },
            label = { Text("Home", color =isSelected(home)) },
            colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent)
        )

        NavigationBarItem(
            selected = false,
            onClick = {  },
            icon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = isSelected(search)) },
            label = { Text("Search", color = isSelected(search)) }
        )

        NavigationBarItem(
            selected = false,
            onClick = { /* Navigate Watchlist */ },
            icon = { Icon(Icons.Default.Search, contentDescription = "Watchlist", tint = isSelected(watchlist)) },
            label = { Text("Watchlist", color = isSelected(watchlist)) }
        )

        NavigationBarItem(
            selected = false,
            onClick = { /* Navigate Profile */ },
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile", tint=isSelected(profile)) },
            label = { Text("Profile", color = isSelected(profile)) }
        )
    }
}
fun isSelected(item:Boolean): Color{
  return if (item) OrangeAccent else Color.LightGray
}