plugins {
    alias(libs.plugins.cityPark.android.feature)
}

android {
    namespace = "ge.tbca.city_park.payment.presentation"
}

dependencies {
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.espresso.core)
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.feature.payment.domain)
}