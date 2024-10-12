import org.jetbrains.compose.internal.utils.getLocalProperty
import java.net.URI

plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.library").version(libs.versions.android).apply(false)
    kotlin("multiplatform").version(libs.versions.kotlin).apply(false)
    id("com.android.application") version libs.versions.android apply false
    id("org.jetbrains.kotlin.android") version libs.versions.kotlin apply false
    alias(libs.plugins.jetbrainsCompose) apply false

    // Existing plugins
    alias(libs.plugins.compose.compiler) apply false
    id("project-report")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

allprojects {
    repositories {
        maven {
            name = "skamirmaps"
            url = project.getLocalProperty("SKAMIRMAPS_REPO_URL")?.let {
                URI(it)
            } ?: error("SKAMIR Maps: Repo URL is not configured properly.")
            credentials {
                username = "_json_key_base64"
                password = project.getLocalProperty("SKAMIRMAPS_REPO_PASSWORD")
                    ?: error("SKAMIR Maps: Repo password is not confiured properly.")
            }
        }
        google()
        mavenCentral()
    }
}

