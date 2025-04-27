plugins {
    alias(libs.plugins.cityPark.android.feature)
}

android {
    namespace = "ge.tbca.city_park.more.presentation"
}

dependencies {
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.junit)
    testImplementation(libs.androidx.espresso.core)
}

dependencies {
    implementation(projects.feature.settings.presentation)
}