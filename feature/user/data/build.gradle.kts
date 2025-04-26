plugins {
    alias(libs.plugins.cityPark.android.library)
    alias(libs.plugins.cityPark.dagger.hilt)
    alias(libs.plugins.cityPark.retrofit)
}

android {
    namespace = "ge.tbca.city_park.user.data"
}

dependencies {
    implementation(libs.firebase.auth)
}

dependencies {
    implementation(projects.feature.user.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)
}
