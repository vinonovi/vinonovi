plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.vinonovi"
    compileSdk = 34

    val key: String = com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir)
        .getProperty("SUPABASE_ANON_KEY")
    val url: String = com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir)
        .getProperty("SUPABASE_URL")

    buildFeatures {
        buildConfig = true // 이 부분을 추가하여 사용자 지정 BuildConfig 필드를 활성화합니다.
    }

    defaultConfig {
        applicationId = "com.example.vinonovi"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        // set value part
        buildConfigField("String", "SUPABASE_ANON_KEY", "\"$key\"")
//        buildConfigField("String", "SECRET", "\"${properties.getProperty("SECRET")}\"")
        buildConfigField("String", "SUPABASE_URL", "\"$url\"")
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
        mlModelBinding = true
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

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("org.tensorflow:tensorflow-lite-support:0.1.0")
    implementation("org.tensorflow:tensorflow-lite-metadata:0.1.0")
    implementation("org.tensorflow:tensorflow-lite-gpu:2.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    // room 2.6.0 에러있음
    val room_version = "2.5.0"
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    // landscapist glide
    implementation("com.github.skydoves:landscapist-glide:2.2.12")
    val navVersion = "2.7.5"
    implementation("androidx.navigation:navigation-compose:$navVersion")
    // supabase 인증
    implementation("io.github.jan-tennert.supabase:gotrue-kt:1.4.7")
    implementation("io.github.jan-tennert.supabase:compose-auth:1.4.7")
    implementation("io.github.jan-tennert.supabase:compose-auth-ui:1.4.7")
    implementation("io.ktor:ktor-client-cio:2.3.6")
}