plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    //Dagger 2
    id("kotlin-kapt")

    //Room
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.andef.dailyquizandef"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.andef.dailyquizandef"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

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
    //Dagger 2
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    //Jetpack Compose Navigation
    implementation(libs.androidx.navigation.compose)

    //Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    //Retrofit
    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //Splash Screen
    implementation(libs.androidx.core.splashscreen)

    //System Ui Controller
    implementation(libs.accompanist.systemuicontroller)

    //core
    implementation(project(":core:data"))
    implementation(project(":core:design"))
    implementation(project(":core:di:common"))
    implementation(project(":core:di:viewmodel"))
    implementation(project(":core:domain"))
    implementation(project(":core:navigation:graph"))
    implementation(project(":core:navigation:routes"))

    //feature:start
    implementation(project(":feature:start:presentation"))

    //feature:quiz
    implementation(project(":feature:quiz:di"))
    implementation(project(":feature:quiz:domain"))
    implementation(project(":feature:quiz:presentation"))
    implementation(project(":feature:quiz:data"))

    //feature:history
    implementation(project(":feature:history:di"))
    implementation(project(":feature:history:domain"))
    implementation(project(":feature:history:presentation"))

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
}