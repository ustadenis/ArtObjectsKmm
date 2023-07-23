plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.compose")
}

android {
    namespace = "com.ssa.aholdtest.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.ssa.aholdtest.android"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:1.4.3")
    implementation("androidx.compose.ui:ui-tooling:1.4.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")
    implementation("androidx.compose.foundation:foundation:1.4.3")
    implementation("androidx.compose.material:material:1.4.3")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.27.0")

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

    implementation("io.insert-koin:koin-android:3.4.2")
    implementation("io.insert-koin:koin-core:3.4.2")
    implementation("io.insert-koin:koin-androidx-compose:3.4.5")

    compileOnly("io.github.alexgladkov:odyssey-core:1.3.1")
    compileOnly("io.github.alexgladkov:odyssey-compose:1.3.1")
    compileOnly("io.github.alexgladkov:odyssey-compose-android:1.3.1")

    compileOnly("io.github.aakira:napier:2.6.1")
}