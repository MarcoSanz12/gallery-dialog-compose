# Mantener entrada de la librería
-keep class com.marcosanz.panorama_dialog.PanoramaDialogOptions { *; }
-keep class com.marcosanz.panorama_dialog.PanoramaDialogKt { *; }

# REGLAS PARA PANORAMAGL (Fundamental para que no pete el motor gráfico)
-keep class com.panoramagl.** { *; }
-keep interface com.panoramagl.** { *; }
-keepclassmembers class com.panoramagl.** {
    public *;
}

# Evitar que se borren métodos nativos
-keepclasseswithmembernames class * {
    native <methods>;
}