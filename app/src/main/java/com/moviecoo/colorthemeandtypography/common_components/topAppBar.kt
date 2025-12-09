package com.moviecoo.colorthemeandtypography.common_components

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.moviecoo.colorthemeandtypography.R
import com.moviecoo.colorthemeandtypography.ui.theme.Surface

/**
 * ## TopAppBar (Center Aligned)
 * A customizable [CenterAlignedTopAppBar] component for the application.
 *
 * This component supports displaying a title and conditionally showing a
 * back navigation button that utilizes the Android system's Back Dispatcher.
 *
 * @param showBackButton If true, an "ArrowBack" icon is displayed in the navigation slot.
 * @param title The string resource ID for the title text. Defaults to R.string.moodToMovie.
 * @param backgroundColor The background color of the TopAppBar. Note: The current implementation
 * overrides this parameter with a hardcoded white color in TopAppBarDefaults.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    showBackButton: Boolean = false,
    @StringRes title: Int  = R.string.moodToMovie,
    // NOTE: This parameter is currently overridden by the hardcoded color in TopAppBarDefaults.
    backgroundColor: Color = Surface
) {
    // Retrieves the system's OnBackPressedDispatcher, which handles system back presses.
    // This allows the back button to behave like the system back action.
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    CenterAlignedTopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 3.dp), // Adds a small padding at the top

        title = {
            // Displays the title string loaded from the resource ID.
            Text(
                text = stringResource(title),
                color = Color.Black
            )
        },

        // Defines the visual appearance (colors) of the TopAppBar.
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            // NOTE: The containerColor is hardcoded to white (0xFFFFFFFF), ignoring the 'backgroundColor' parameter.
            containerColor = Color(0xFFFFFFFF)
        ),

        // Slot for leading icons, typically used for the back button or navigation drawer toggle.
        navigationIcon = {
            IconButton(
                // Executes the system's back action when the button is pressed.
                onClick = { backDispatcher?.onBackPressed() }
            ){
                if (showBackButton){
                    // Display the back icon only if showBackButton is true.
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back", // Use a descriptive string for accessibility
                        tint = Color.Black
                    )
                }
            }
        }
    )
}