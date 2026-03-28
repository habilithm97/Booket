plugins {
    alias(libs.plugins.android.application)
}
val kakaoApiKey: String = project.findProperty("KAKAO_API_KEY") as? String ?: ""

android {
    namespace = "com.example.booket"
    compileSdk {
        version = release(36)
    }
    defaultConfig {
        applicationId = "com.example.booket"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "KAKAO_API_KEY", "\"$kakaoApiKey\"")
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
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.retrofit) // REST API 통신을 위한 HTTP 클라이언트
    implementation(libs.retrofit.gson) // JSON 데이터를 코틀린 객체로 변환 (Gson Converter)
    implementation(libs.okhttp.logging) // 네트워크 요청 및 응답 로그 확인 (디버깅용)

    implementation(libs.lifecycle.viewmodel.ktx)

    implementation(libs.androidx.navigation.fragment)

    implementation(libs.glide)
}