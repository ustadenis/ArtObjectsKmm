plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization") version "1.8.22"
    id("com.android.library")
    id("org.jetbrains.compose")
    id("dev.icerock.mobile.multiplatform-resources")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
            export("dev.icerock.moko:resources:0.23.0")
            export("dev.icerock.moko:graphics:0.9.0")
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":shared:data"))
                implementation(project(":shared:domain"))
                //put your multiplatform dependencies here
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.material3)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation(compose.materialIconsExtended)

                implementation("io.ktor:ktor-client-core:2.3.2")
                implementation("io.ktor:ktor-client-content-negotiation:2.3.2")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.2")

                api("com.rickclephas.kmm:kmm-viewmodel-core:1.0.0-ALPHA-6")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
                implementation("io.insert-koin:koin-core:3.4.2")
                api("dev.icerock.moko:resources:0.23.0")
                api("dev.icerock.moko:resources-compose:0.23.0")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

                implementation("io.github.alexgladkov:odyssey-core:1.3.1")
                implementation("io.github.alexgladkov:odyssey-compose:1.3.1")

                implementation("io.github.aakira:napier:2.6.1")

                implementation("media.kamel:kamel-image:0.6.1")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:2.3.2")

                implementation("androidx.paging:paging-runtime:3.1.1")
                implementation("androidx.paging:paging-compose:1.0.0-alpha18")

                implementation("io.insert-koin:koin-android:3.4.2")
                implementation("io.insert-koin:koin-core:3.4.2")
                implementation("io.insert-koin:koin-androidx-compose:3.4.5")
            }
        }
        val iosSimulatorArm64Main by getting {
            dependencies {
                implementation("io.github.alexgladkov:odyssey-compose-iosSimulatorArm64:1.4.0-alpha05")
            }
        }
        val iosX64Main by getting {
            dependencies {
                implementation("io.github.alexgladkov:odyssey-compose-iosX64:1.4.0-alpha05")
            }
        }
        val iosArm64Main by getting {
            dependencies {
                implementation("io.github.alexgladkov:odyssey-compose-iosArm64:1.4.0-alpha05")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-darwin:2.3.2")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidUnitTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
                implementation("io.mockk:mockk:1.10.2")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")
                implementation("androidx.arch.core:core-testing:2.2.0")
            }
        }
    }
}

android {
    namespace = "com.ssa.aholdtest"
    compileSdk = 33
    defaultConfig {
        minSdk = 29
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    testOptions {
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.ssa.aholdtest"
    multiplatformResourcesClassName = "Res"
}