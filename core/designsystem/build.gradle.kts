plugins {
    alias(libs.plugins.cityPark.android.library.compose)
}

android {
     namespace = "ge.tbca.city_park.core.designsystem"
}

dependencies{
    implementation(libs.compose.lottie)

}