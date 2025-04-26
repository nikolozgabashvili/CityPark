plugins{
    alias(libs.plugins.cityPark.android.library.compose)
}

android{
    namespace = "ge.tbca.city_park.core.ui"
}

dependencies{
    implementation(projects.core.domain)
}