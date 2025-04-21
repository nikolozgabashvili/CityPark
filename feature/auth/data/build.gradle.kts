plugins{
    alias(libs.plugins.cityPark.android.library)
    alias(libs.plugins.cityPark.dagger.hilt)
}

android{
    namespace = "ge.tbca.city_park.auth.data"
}

dependencies{
    implementation(libs.firebase.auth)
    implementation(projects.feature.auth.domain)
    implementation(projects.core.domain)

}