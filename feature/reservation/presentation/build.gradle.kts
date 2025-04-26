plugins {
    alias(libs.plugins.cityPark.android.feature)
}

android {
    namespace = "ge.tba.city_park.reservation.presentation"
}

dependencies {
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.espresso.core)
}

dependencies {
    implementation(projects.feature.reservation.domain)
    implementation(projects.core.domain)
    implementation(projects.feature.cars.presentation)
    implementation(projects.feature.cars.domain)
}