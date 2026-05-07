package com.marcosanz.gallery_core_ui.theme

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

// Preview que combina el modo Claro y Oscuro en la misma
@Preview(
    name = "Light",
    group = "UI Themes",
    showBackground = true,
    uiMode = Configuration.UI_MODE_TYPE_NORMAL,
    backgroundColor = 0xFFF4F4F4,
)
@Preview(
    name = "Dark",
    group = "UI Themes",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    backgroundColor = 0xFF122125
)
annotation class PreviewLightDarkV2

@Preview(name = "Standard", device = "id:pixel_5")
@Preview(device = "id:4.65in 720p (Galaxy Nexus)", name = "Chiquito 30 Font x2", fontScale = 2.0f)
@Preview(device = "spec:parent=Nexus 9,orientation=portrait", name = "Megatablet")
annotation class PreviewHardcore