package com.moviecoo.colorthemeandtypography.common_components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier,
              showBackButton: Boolean,
              @StringRes title: Int,
              backgroundColor: Color,
              backButtonClicked: ()-> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.background(backgroundColor),
        title = { Text(text = stringResource(title)) },
        navigationIcon = {
            IconButton(
                onClick = {backButtonClicked()}
            ) {
                if (showBackButton){
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )

                }
            }
        }
    )

}