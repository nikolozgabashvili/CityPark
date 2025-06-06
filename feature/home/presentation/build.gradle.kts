plugins {
    alias(libs.plugins.cityPark.android.feature)
}

android {
    namespace = "ge.tbca.city_park.home.presentation"
}

dependencies {

    implementation(libs.accompanist.permissions)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.espresso.core)
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.feature.cars.domain)
    implementation(projects.feature.cars.presentation)

    implementation(projects.feature.user.domain)
    implementation(projects.feature.user.presentation)

    implementation(projects.feature.reservation.domain)
}