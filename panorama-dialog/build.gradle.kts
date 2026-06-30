plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.marcosanz.panorama_dialog"

    buildFeatures {
        compose = true
    }
}

dependencies {
    api(project(":gallery-common"))
    implementation(project(":gallery-core-ui"))
    implementation(platform(libs.compose.bom))
    implementation(platform(libs.coil.bom))

    implementation(libs.bundles.ui.panorama)
    // TEST
    testImplementation(libs.bundles.test.core)

    androidTestImplementation(libs.bundles.test.android.core)
    androidTestImplementation(platform(libs.compose.bom))
}