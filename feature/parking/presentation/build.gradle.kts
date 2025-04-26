plugins {
    alias(libs.plugins.cityPark.android.feature)
}

android {
    namespace = "ge.tba.city_park.parking.presentation"
}

dependencies {
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.espresso.core)
}

dependencies {
    implementation(projects.feature.parking.domain)
    implementation(projects.core.domain)
}