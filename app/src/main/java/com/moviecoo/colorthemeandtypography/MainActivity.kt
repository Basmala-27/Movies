package com.moviecoo.colorthemeandtypography

import SplashScreen
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.moviecoo.colorthemeandtypography.ui.theme.ColorThemeandTypographyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ColorThemeandTypographyTheme {
                SplashScreen(
                    onTomeOut = {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()

                    }
                )

            }
        }
    }
}

