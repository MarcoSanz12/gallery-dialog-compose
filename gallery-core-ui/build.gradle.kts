plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.marcosanz.gallery_core_ui"
    compileSdk {
        version = release(36)
    }
    resourcePrefix = "gallery_dialog_"

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {
    // CORE
    api(libs.bundles.core)

    // UI
    implementation(platform(libs.compose.bom))
    implementation(platform(libs.coil.bom))
    api(libs.bundles.ui)

    // TEST
    testImplementation(libs.bundles.test.core)

    androidTestImplementation(libs.bundles.test.android.core)
    androidTestImplementation(platform(libs.compose.bom))
}