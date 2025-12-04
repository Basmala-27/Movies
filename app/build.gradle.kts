import java.io.File
import java.io.FileInputStream
import java.util.Properties

val properties = Properties()
properties.load(FileInputStream(rootProject.file("local.properties")))

rootProject.ext.set("GEMINI_API_KEY", properties.getProperty("GEMINI_API_KEY"))

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services")

    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.moviecoo.colorthemeandtypography"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.moviecoo.colorthemeandtypography"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        val geminiApiKey = project.findProperty("GEMINI_API_KEY") as String? ?: ""
        buildConfigField("String", "GEMINI_API_KEY", "\"$geminiApiKey\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
}

dependencies {

    implementation(libs.androidx.compose.runtime.livedata)
    implementation("androidx.compose.animation:animation:1.7.4")
    implementation("androidx.compose.material:material-icons-extended:1.7.5") // Latest

    // Compose & Navigation
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.navigation.compose)

    // Gemini AI SDK (FIXED DEPENDENCY)
    implementation("com.google.ai.client.generativeai:generativeai:0.9.0")

    // Coroutines (Ensures compatibility)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1") // Updated version

    // Core Android/Kotlin
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.coil.compose)
    implementation(libs.coil)
    implementation("com.google.code.gson:gson:2.13.2")
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.ui.text)
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("androidx.compose.animation:animation:1.7.4")
    implementation("androidx.compose.material:material-icons-extended-android:1.6.6")
    implementation(libs.coil.compose)
    implementation(libs.coil)
    implementation("io.coil-kt:coil-compose:2.6.0")



    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")

    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
    implementation("com.google.firebase:firebase-auth-ktx")


    // Networking
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Image Loading
    implementation("io.coil-kt:coil-compose:2.6.0")

    // Compose
    implementation("androidx.compose.material:material-icons-extended:1.7.5")
    implementation("androidx.compose.animation:animation:1.7.4")
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")

    // AndroidX Libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.navigation.compose)
    implementation("com.google.dagger:hilt-android:2.52")
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.media3.common.ktx)
    implementation(libs.firebase.crashlytics.buildtools)
    implementation("androidx.compose.material3:material3:1.2.1")

    kapt("com.google.dagger:hilt-android-compiler:2.52")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")
    // Compose BOM
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.text)

    // Material Design
    implementation(libs.material)


    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    implementation("com.airbnb.android:lottie-compose:6.3.0")
    // Example: The actual dependency name might vary slightly

    // Change the core dependency to the latest stable version
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:13.0.0")



    implementation("com.google.ai.client.generativeai:generativeai:0.7.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.datastore:datastore-core:1.0.0")

    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
    implementation("com.google.firebase:firebase-firestore-ktx")


    // Image Loading
    implementation("io.coil-kt:coil-compose:2.6.0")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
    implementation("com.google.firebase:firebase-auth-ktx")

    // YouTube/Lottie/Other
    implementation("com.airbnb.android:lottie-compose:6.3.0")
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:13.0.0")
    implementation("com.google.code.gson:gson:2.10.1")
}