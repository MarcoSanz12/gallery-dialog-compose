// build-logic/convention/build.gradle.kts
plugins {
    `kotlin-dsl`
}

group = "com.marcosanz.app.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    // El plugin de Compose ahora es parte de Kotlin 2.0+ y no suele necesitar un classpath aparte aquí 
    // si ya tienes el kotlin-gradle-plugin, pero si fuera necesario se añadiría el artifact correspondiente.
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "convention.android.library"
            implementationClass = "es.cotesa.app.buildlogic.AndroidLibraryConventionPlugin"
        }
    }
}
