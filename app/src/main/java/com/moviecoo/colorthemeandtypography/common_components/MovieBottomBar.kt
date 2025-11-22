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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.moviecoo.colorthemeandtypography.ui.theme.OrangeAccent
import com.moviecoo.colorthemeandtypography.ui.theme.Surface


@Composable
fun MovieBottomBar(home: Boolean = false , search: Boolean =false , watchlist: Boolean = false, profile: Boolean= false
,onHomeClick: () -> Unit ={}, onSearchClick: () -> Unit ={}, onWatchlistClick: () -> Unit ={} , onProfileClick: () -> Unit={}, navController: NavController
) {
    var selectedItem by remember { mutableStateOf("home") }



    NavigationBar(
        containerColor = Surface ,
    ) {

        NavigationBarItem(
            selected = home,
            onClick = {onHomeClick() },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home", tint = isSelected(home) ) },
            label = { Text("Home", color =isSelected(home)) },
            colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent)
        )

        NavigationBarItem(
            selected = selectedItem == "search",
            onClick = { onSearchClick() },
            icon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = isSelected(search)) },
            label = { Text("Search", color = isSelected(search)) }
        )

        NavigationBarItem(
            selected = watchlist,
            onClick = {  onWatchlistClick()},
            icon = { Icon(Icons.Default.FavoriteBorder, contentDescription = "Watchlist", tint = isSelected(watchlist)) },
            label = { Text("Watchlist", color = isSelected(watchlist)) }
        )

        NavigationBarItem(
            selected = profile,
            onClick = { onProfileClick() },
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile", tint=isSelected(profile)) },
        )
    }
}
fun isSelected(item:Boolean): Color{
  return if (item) OrangeAccent else Color.LightGray
}