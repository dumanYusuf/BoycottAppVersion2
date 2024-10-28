plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.dumanyusuf.boycottapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dumanyusuf.boycottapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 3
        versionName = "1.8.1"

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    // firebase Boom
    implementation(platform("com.google.firebase:firebase-bom:33.3.0"))

    // Coil
    implementation ("io.coil-kt:coil-compose:2.7.0")

    // viewModel
    implementation("androidx.navigation:navigation-compose:2.7.7")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0")


    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

    // Coroutine Lifecycle Scopes
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")


    // cloud firestore
    implementation("com.google.firebase:firebase-firestore")

    // hilt
    implementation ("com.google.dagger:hilt-android:2.52")
    kapt ("com.google.dagger:hilt-compiler:2.52")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    //jetpack sayfalar arası geçiş kurulum
    implementation("androidx.navigation:navigation-compose:2.7.7")

// sayfalar arası veri transferi
    implementation("com.google.code.gson:gson:2.10.1")

    // play servis
    implementation("com.google.android.gms:play-services-ads:23.3.0")

    // splashScrean
    implementation("androidx.core:core-splashscreen:1.0.0")

    // gemini dependiws
    implementation(libs.generativeai)
}