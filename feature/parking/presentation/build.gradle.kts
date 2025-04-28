plugins {
    alias(libs.plugins.cityPark.android.feature)
    alias(libs.plugins.mapsplatform.secrets.plugin)
}

android {
    namespace = "ge.tba.city_park.parking.presentation"
}

dependencies {
    implementation(libs.play.services.maps)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.espresso.core)

    implementation(libs.maps.compose)
    implementation(libs.maps.compose.utils)

    implementation(libs.accompanist.permissions)
}

dependencies {
    implementation(projects.feature.parking.domain)
    implementation(projects.core.domain)
    
    implementation(projects.feature.cars.domain)
    implementation(projects.feature.cars.presentation)

    implementation(projects.feature.reservation.domain)
}