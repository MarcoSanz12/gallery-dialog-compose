# Mantener el FileProvider personalizado para que no falle al compartir archivos
-keep class com.marcosanz.gallery_core_ui.util.GalleryFileProvider { *; }

# Mantener componentes de UI y temas si se acceden por nombre
-keep class com.marcosanz.gallery_core_ui.theme.** { *; }

# Reglas generales para Compose
-keepclassmembers class * {
    @androidx.compose.runtime.Composable <methods>;
}