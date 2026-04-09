package com.marcosanz.app.core.ui.theme

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


@Preview("Pixel Tablet", device = "id:pixel_tablet")
annotation class PreviewTablet

@Preview("Pixel Tablet", device = "id:pixel_tablet")
@Preview(name = "Pixel Tablet x2", device = "id:medium_tablet", apiLevel = 30)
@Preview(name = "Pixel Tablet x2", device = "id:pixel_tablet", fontScale = 2.0f)
annotation class PreviewHardcoreTablet