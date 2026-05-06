plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.marcosanz.app"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.marcosanz.gallerydialog_compose"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    bundle {
        language {
            enableSplit = false
        }
    }

    // TODO Declarar idiomas de la app
    androidResources {
        localeFilters += listOf("es", "en", "fr", "de")
    }

    configurations.all {
        resolutionStrategy {
            force("org.jetbrains:annotations:23.0.0")
        }
        exclude(group = "com.intellij", module = "annotations")
    }

    packaging {
        resources {
            pickFirsts.add("META-INF/gradle/incremental.annotation.processors")
        }
    }

    /*
        signingConfigs {
         create("release") {
             storeFile = file("key/base.jks")
             storePassword = "Base_Password"
             keyAlias = "key0"
             keyPassword = "Base_Password"
         }
     }*/
    buildTypes {
        debug {
            isDebuggable = true
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            //TODO signingConfig = signingConfigs.getByName("release")
        }
    }

    flavorDimensions += "version"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {
    ksp(libs.hilt.android.compiler)
    ksp(libs.androidx.hilt.compiler)

    implementation(project(":gallery_dialog"))
    implementation(project(":core-ui"))
    implementation(platform(libs.compose.bom))
    implementation(platform(libs.coil.bom))

    // DI - Hilt
    implementation(libs.bundles.di)
    // TEST
    testImplementation(libs.bundles.test.core)

    androidTestImplementation(libs.bundles.test.android.core)
    androidTestImplementation(platform(libs.compose.bom))
}