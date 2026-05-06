package com.marcosanz.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.marcosanz.app.feature.MainScreen
import com.marcosanz.core_ui.theme.GalleryTheme
import com.marcosanz.core_ui.util.controller.EdgeToEdgeController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EdgeToEdgeController.enableEdgeToEdge(
            activity = this,
            mode = EdgeToEdgeController.Mode.LIGHT
        )
        setContent {
            GalleryTheme {
                Scaffold(
                    content = { paddingValues ->
                        Box(modifier = Modifier.padding(paddingValues)) {
                            MainScreen()
                        }
                    }
                )
            }
        }
    }
}