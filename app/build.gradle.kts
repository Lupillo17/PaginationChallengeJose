import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlinx-serialization")
}

val localProperties = Properties().apply {
    load(FileInputStream(rootProject.file("local.properties")))
}

val marvelPrivateKey: String = localProperties.getProperty("marvel.pv.key")
val marvelPublicKey: String = localProperties.getProperty("marvel.pu.key")

android {
    namespace = "com.example.paginationchallenge"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.paginationchallenge"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField(type = "String", name = "PRIVATE_KEY", value = marvelPrivateKey)
        buildConfigField(type = "String", name = "PUBLIC_KEY", value = marvelPublicKey)
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    //Android Core
    implementation("androidx.core:core-ktx:1.9.0")

    //Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    //Compose
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material:1.5.4")

    //Added - Pagination
    implementation("androidx.paging:paging-compose:3.2.1")

    //Added - Coil
    implementation("io.coil-kt:coil-compose:2.4.0")

    //Added - Koin
    implementation("io.insert-koin:koin-android:3.3.2")
    implementation("io.insert-koin:koin-androidx-compose:3.4.1")
    implementation("io.insert-koin:koin-android-compat:3.3.2")

    //Added - Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    //Added - Ktor
    implementation("io.ktor:ktor-client-android:1.5.0")
    implementation("io.ktor:ktor-client-serialization:1.5.0")
    implementation("io.ktor:ktor-client-logging-jvm:1.5.0")

    //Added - Navigation
    implementation("androidx.navigation:navigation-compose:2.7.5")

    //Tooling & testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}