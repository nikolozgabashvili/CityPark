plugins {
    alias(libs.plugins.cityPark.android.feature)
}

android {
    namespace = "ge.tbca.city_park.user.presentation"
}

dependencies {
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.junit)
    testImplementation(libs.androidx.espresso.core)
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.feature.user.domain)
    implementation(projects.feature.messaging.domain)
    implementation(projects.feature.auth.domain)
    implementation(projects.feature.auth.presentation)
    implementation(projects.feature.reservation.domain)
    implementation(projects.feature.reservation.presentation)
}