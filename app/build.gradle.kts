plugins {
    alias(libs.plugins.cityPark.application.compose)
    alias(libs.plugins.cityPark.dagger.hilt)
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.kotlin.serialization)
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

    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    testImplementation(libs.kotlinx.coroutines.test)

    // mockk
    testImplementation(libs.mockk)

    // navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    // serialization
    implementation(libs.serialization.json)

    //worker
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.hilt.work)
    ksp(libs.androidx.hilt.work.compiler)
}

dependencies {
    implementation(projects.feature.settings.data)
    implementation(projects.feature.settings.domain)

    implementation(projects.feature.auth.data)
    implementation(projects.feature.auth.domain)
    implementation(projects.feature.auth.presentation)

    implementation(projects.core.designsystem)
    implementation(projects.core.data)

    implementation(projects.datastore.data)

    implementation(projects.feature.payment.presentation)
    implementation(projects.feature.payment.domain)
    implementation(projects.feature.payment.data)

    implementation(projects.feature.cars.presentation)
    implementation(projects.feature.cars.domain)
    implementation(projects.feature.cars.data)

    implementation(projects.feature.reservation.presentation)
    implementation(projects.feature.reservation.domain)
    implementation(projects.feature.reservation.data)
}