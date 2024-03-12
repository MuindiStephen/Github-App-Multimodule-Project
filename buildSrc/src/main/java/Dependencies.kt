object Version {
    const val lifeCycleVersion = "2.7.0"
    const val timberVersion = "5.0.1"
    const val navigationVersion = "2.7.7"
    const val coroutinesVersion = "1.7.3"
    const val retrofitVersion = "2.9.0"
    const val logginInterceptorVersion = "5.0.0-alpha.1"
    const val gsonVersions = "2.10.1"
    const val converterGsonVersionV = "2.9.0"
    const val daggerHiltVersion = "2.50"
}

object Deps {
    const val lifeCycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifeCycleVersion}"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Version.navigationVersion}"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Version.navigationVersion}"
    const val daggerHilt = "com.google.dagger:hilt-android:${Version.daggerHiltVersion}"
    const val daggerHiltCompiler = "com.google.dagger:hilt-compiler:${Version.daggerHiltVersion}"
    const val timberLogger = "com.jakewharton.timber:timber:${Version.timberVersion}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofitVersion}"
    const val logginInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.logginInterceptorVersion}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutinesVersion}"
    const val gsonV = "com.google.code.gson:gson:${Version.gsonVersions}"
    const val gsonConverterV = "com.squareup.retrofit2:converter-gson:${Version.converterGsonVersionV}"
}