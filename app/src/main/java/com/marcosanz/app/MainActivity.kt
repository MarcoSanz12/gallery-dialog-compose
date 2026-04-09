package com.marcosanz.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.marcosanz.app.core.ui.theme.BaseTheme
import com.marcosanz.app.util.controller.EdgeToEdgeController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EdgeToEdgeController.enableEdgeToEdge(
            activity = this,
            mode = EdgeToEdgeController.Mode.LIGHT
        )
        setContent {
            BaseTheme {
            }
        }
    }
}