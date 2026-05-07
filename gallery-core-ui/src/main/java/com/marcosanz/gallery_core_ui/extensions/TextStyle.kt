package com.marcosanz.gallery_core_ui.extensions

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle


/**
 * Devuelve una copia del [TextStyle] pero quitando el color y el fontWeight para que sea más compatible al usar en un Tema
 */
fun TextStyle.copyForTheme() = copy(color = Color.Unspecified, fontWeight = null)