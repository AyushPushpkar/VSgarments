plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
//    alias(libs.plugins.google.gms.google.services)
    id("com.google.gms.google-services")
    id ("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.kapt")
    id ("kotlin-parcelize")

}

android {
    namespace = "com.example.vsgarments"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.vsgarments"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.9"
    }

    packaging {
        resources {
            excludes += "META-INF/versions/9/OSGI-INF/MANIFEST.MF"
        }
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
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.database)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage)
    implementation(libs.identity.jvm)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

   // implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.21")


    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.core.ktx.v170)
    implementation(libs.coil.compose)

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("androidx.core:core-ktx:1.7.0")

    //navigation
    implementation ("androidx.navigation:navigation-compose:2.7.7")
    implementation("io.appwrite:sdk-for-android:5.1.0")

    //Gson
    implementation ("com.google.code.gson:gson:2.11.0")

    //firebase
    implementation ("com.google.firebase:firebase-analytics:22.1.2")
    implementation( "com.google.firebase:firebase-firestore:25.1.1")
    implementation ("com.google.firebase:firebase-auth:23.1.0")
    implementation ("com.google.firebase:firebase-database:21.0.0")
    implementation ("com.google.firebase:firebase-storage:21.0.1")

    implementation ("com.google.dagger:hilt-android:2.48")
    kapt ("com.google.dagger:hilt-android-compiler:2.48")

    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")

    //fancy toast
    implementation(libs.fancytoast)
    //custom toast
    implementation (libs.android.custom.toast.message)

    //image show
    implementation("io.coil-kt:coil-compose:2.2.2")

    //appwrite
    implementation("io.appwrite:sdk-for-android:6.1.0")

    //shimmer
    implementation("com.valentinilk.shimmer:compose-shimmer:1.2.0")

    //razorpay
    implementation("com.razorpay:checkout:1.6.26")


}