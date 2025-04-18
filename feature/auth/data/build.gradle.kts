plugins{
    alias(libs.plugins.cityPark.android.library)
}

android{
    namespace = "ge.tbca.city_park.auth.data"
}

dependencies{
    implementation(projects.feature.auth.domain)

}