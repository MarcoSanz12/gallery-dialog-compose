import com.android.build.api.dsl.LibraryExtension

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.ksp) apply false
}

subprojects {
    val subproject = this
    plugins.withId("com.android.library") {
        apply(plugin = "maven-publish")

        extensions.configure<LibraryExtension> {
            publishing {
                singleVariant("release")
            }
        }

        afterEvaluate {
            extensions.configure<PublishingExtension> {
                publications {
                    register<MavenPublication>("release") {
                        groupId = project.property("GROUP_ID").toString()
                        artifactId = subproject.name
                        version = project.property("LIBRARY_VERSION").toString()

                        afterEvaluate {
                            from(components["release"])
                        }
                    }
                }
            }
        }
    }
}