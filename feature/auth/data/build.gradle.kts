plugins{
    alias(libs.plugins.cityPark.android.library)
    alias(libs.plugins.cityPark.dagger.hilt)
    alias(libs.plugins.cityPark.retrofit)
}

android{
    namespace = "ge.tbca.city_park.auth.data"
}

dependencies{
    implementation(libs.firebase.auth)

    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.hilt.work)
    ksp(libs.androidx.hilt.work.compiler)

}

dependencies{
    implementation(projects.feature.auth.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)
}