plugins {
    alias(libs.plugins.cityPark.application.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "ge.tbca.city_park"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.firebase.auth)
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
    implementation(libs.firebase.database)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    // coil
    implementation(libs.coil.compose)

    // preferences datastore
    implementation(libs.preferences.datastore)

    // serialization
    implementation(libs.serialization.json)

    // lottie
    implementation(libs.compose.lottie)

    // okhttp
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)

    // retrofit serialization
    implementation(libs.retrofit.serialization)

    // hilt
    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.compiler)
}