package com.moviecoo.colorthemeandtypography.common_components


import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import com.moviecoo.colorthemeandtypography.ui.theme.Surface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    showBackButton: Boolean,
    @StringRes title: Int,
    backgroundColor: Color = Surface,
    backButtonClicked: ()-> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = { Text(text = stringResource(title),
            color = Color.White) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = backgroundColor
        ),
        navigationIcon = {
            IconButton(
                onClick = {backButtonClicked()}
            ) {
                if (showBackButton){
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.White

                    )

                }
            }
        }
    )

}

