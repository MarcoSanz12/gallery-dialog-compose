plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.marcosanz.panorama_dialog"
    compileSdk {
        version = release(36)
    }

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
    api(project(":gallery-common"))
    implementation(project(":gallery-core-ui"))
    implementation(platform(libs.compose.bom))
    implementation(platform(libs.coil.bom))

    implementation(libs.bundles.ui.coil)
    implementation(libs.bundles.ui.panorama)
    // TEST
    testImplementation(libs.bundles.test.core)

    androidTestImplementation(libs.bundles.test.android.core)
    androidTestImplementation(platform(libs.compose.bom))
}