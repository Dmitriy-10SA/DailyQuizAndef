plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)

    //Dagger 2
    id("kotlin-kapt")

    //Room
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.andef.dailyquiz.core.di.common"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    //Dagger 2
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    //Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    //Retrofit
    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //core:di:viewmodel
    implementation(project(":core:di:viewmodel"))

    //core:data
    implementation(project(":core:data"))

    //core:domain
    implementation(project(":core:domain"))

    //feature:history
    implementation(project(":feature:history:di"))
    implementation(project(":feature:history:domain"))
    implementation(project(":feature:history:presentation"))

    //feature:quiz
    implementation(project(":feature:quiz:di"))
    implementation(project(":feature:quiz:domain"))
    implementation(project(":feature:quiz:presentation"))
    implementation(project(":feature:quiz:data"))

    //feature:start
    implementation(project(":feature:start:presentation"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}