plugins {
    alias(libs.plugins.cityPark.android.library)
    alias(libs.plugins.cityPark.dagger.hilt)
    alias(libs.plugins.cityPark.retrofit)
}


android {
    namespace = "ge.tbca.city_park.fines.data"
}


dependencies {
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.feature.fines.domain)
}