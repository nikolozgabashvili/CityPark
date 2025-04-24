plugins{
    alias(libs.plugins.cityPark.android.library)
    alias(libs.plugins.cityPark.dagger.hilt)
    alias(libs.plugins.cityPark.retrofit)
}

android{
    namespace = "ge.tbca.city_park.core.data"
}

dependencies{
    implementation(projects.core.domain)
}