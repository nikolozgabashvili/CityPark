plugins{
    alias(libs.plugins.cityPark.android.library)
    alias(libs.plugins.cityPark.dagger.hilt)
}

android{
    namespace = "ge.tbca.city_park.payment.data"
}

dependencies{
    implementation(projects.feature.payment.domain)
    implementation(projects.core.domain)
}