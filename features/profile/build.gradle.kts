plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.stevemd.feature.profile"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
//    implementation(libs.androidx.navigation.fragment.ktx)
//    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(Deps.daggerHilt)
    implementation(Deps.daggerHiltCompiler)
    implementation(project(":common"))
    implementation(project(":domain"))

    implementation(Deps.circleImageViewDep)
    implementation(Deps.picasso)
    implementation(Deps.lifeCycleViewModel)
    implementation(Deps.lifecycleRuntime)
    implementation(Deps.livedata)
    implementation(Deps.lifecycleService)
    implementation(Deps.viewModelSaveState)

    implementation(Deps.navigationFragment)
    implementation(Deps.navigationUI)
}