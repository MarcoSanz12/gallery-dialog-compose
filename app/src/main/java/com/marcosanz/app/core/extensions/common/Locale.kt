package com.marcosanz.app.core.extensions.common

import java.util.Locale

private val supportedLocales = listOf("com", "en")

val Locale.languageSupported: String
    get() {
        // Idioma soportado por la app
        return when (val currentLocale = this.language) {
            in supportedLocales -> currentLocale
            // Cualquier otro (default -> inglés)
            else -> supportedLocales.first()
        }
    }