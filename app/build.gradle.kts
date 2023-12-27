plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    //id("org.jetbrains.kotlin.kapt")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.cheerup"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cheerup"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    kapt{
        arguments{
            arg("room.schemaLocation","$projectDir/schemas")
        }
        javacOptions {
            // These options are normally set automatically via the Hilt Gradle plugin, but we
            // set them manually to workaround a bug in the Kotlin 1.5.20
            option("-Adagger.fastInit=ENABLED")
            option("-Adagger.hilt.android.internal.disableAndroidSuperclassValidation=true")
        }
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
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.google.android.material:material:1.3.0-alpha02")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")

    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    //implementation("androidx.lifecycle:lifecycle-view-model-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    //hilt dependencies
//    implementation("com.google.dagger:hilt-android:2.40.5")
//    kapt("com.google.dagger:hilt-android-compiler:2.40.5")
//    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
//    kapt("androidx.hilt:hilt-compiler:1.0.0")
//    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")


    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
  //  implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    kapt("androidx.hilt:hilt-compiler:1.1.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    // hilt dependencies
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48")

    // For instrumentation tests
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48")

    // For local unit tests
    testImplementation("com.google.dagger:hilt-android-testing:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48")

}
kapt{
    correctErrorTypes = true
}