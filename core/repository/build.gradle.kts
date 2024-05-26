plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.googleDevtoolsKsp)
    alias(libs.plugins.daggerHilt)
    alias(libs.plugins.composeCompiler)
}

android {
    namespace = "com.lkw1120.pokedex.core.repository"
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        minSdk = Config.MINIMUM_SDK
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
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {

    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":core:common"))

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)

    implementation(libs.androidx.hilt.navigation)
    implementation(libs.dagger.hilt)
    ksp(libs.dagger.hilt.compiler)

    implementation(libs.squareup.moshi)
    implementation(libs.squareup.moshi.kotlin)

    implementation(libs.timber)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}