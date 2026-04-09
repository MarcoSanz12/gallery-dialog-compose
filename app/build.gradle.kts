plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.marcosanz.gallerydialog"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.marcosanz.gallerydialog_compose"
        minSdk = 23
        targetSdk = 37
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

    /*
        TODO Declarar aquí clave para firmar en release, no es 100% seguro pero bueno ¯\_(ツ)_/¯
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
    compileSdkMinor = 0
}

dependencies {
    // CORE
    implementation(libs.bundles.core)

    // UI
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.ui)
    implementation(libs.bundles.ui.media)

    //PERMISSION
    implementation(libs.accompanist.permissions)

    // TEST
    testImplementation(libs.bundles.test.core)

    androidTestImplementation(libs.bundles.test.android.core)
    androidTestImplementation(platform(libs.compose.bom))
}