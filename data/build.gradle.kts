import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.maplescouter.data"

    compileSdk = 36

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        minSdk = 24

        val properties = Properties()
        val propertiesFile = project.rootProject.file("local.properties")
        if (propertiesFile.exists()) {
            properties.load(propertiesFile.inputStream())
        }

        val apiKey = properties.getProperty("API_KEY") ?: "" // 없으면 빈 문자열
        buildConfigField("String", "API_KEY", "\"${apiKey}\"")

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
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // coroutines
    implementation(libs.coroutines)
    testImplementation(libs.coroutines)
    testImplementation(libs.coroutines.test)

    // network
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)
    implementation(libs.okhttp.interceptor)
    testImplementation(libs.okhttp.mockserver)
    testImplementation(libs.androidx.arch.core)

    implementation(libs.retrofit.gson)

    // json parsing
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)

    // room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // di
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(project(":domain"))

    testImplementation(libs.junit)

    // Robolectric
    // 안드로이드 기기나 에뮬레이터 없이 로컬 JVM 환경에서 안드로이드 코드를 테스트하게 해주는 프레임워크
    // ex) Log, Context, Intent, View 등
    // 속도(앱 설치 X, 테스트 속도가 빠름), 안드로이드 의존성 해결
    testImplementation(libs.robolectric)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}