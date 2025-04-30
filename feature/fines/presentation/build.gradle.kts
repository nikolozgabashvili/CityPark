plugins {
    alias(libs.plugins.cityPark.android.feature)
}

android {
    namespace = "ge.tbca.city_park.fines.presentation"
}

dependencies {
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.junit)
    testImplementation(libs.androidx.espresso.core)
}

dependencies {
    implementation(projects.feature.fines.domain)
    implementation(projects.feature.payment.presentation)
    implementation(projects.feature.payment.domain)
    implementation(projects.core.domain)
}