plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.marcosanz.gallery_core_ui"
    resourcePrefix = "gallery_dialog_"

    buildFeatures {
        compose = true
    }
}

dependencies {
    // CORE
    api(libs.bundles.core)

    // UI
    implementation(platform(libs.compose.bom))
    implementation(platform(libs.coil.bom))
    api(libs.bundles.ui)
    api(libs.bundles.ui.coil)

    // TEST
    testImplementation(libs.bundles.test.core)

    androidTestImplementation(libs.bundles.test.android.core)
    androidTestImplementation(platform(libs.compose.bom))
}