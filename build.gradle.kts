buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        val hiltVersion = "2.48"
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
        // Other classpath dependencies
    }
}

plugins {

    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.21" apply false
}

// Add other configurations if needed