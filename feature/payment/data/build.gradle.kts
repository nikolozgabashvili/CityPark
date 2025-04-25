plugins{
    alias(libs.plugins.cityPark.android.library)
    alias(libs.plugins.cityPark.dagger.hilt)
    alias(libs.plugins.cityPark.retrofit)

}

android{
    namespace = "ge.tbca.city_park.payment.data"
}

dependencies{
    implementation(projects.feature.payment.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)
}