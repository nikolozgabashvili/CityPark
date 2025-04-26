plugins{
    alias(libs.plugins.cityPark.android.feature)
    alias(libs.plugins.cityPark.dagger.hilt)
}

android{
    namespace = "ge.tbca.city_park.messaging.presentation"
}


dependencies{
    implementation(projects.feature.messaging.domain)
    implementation(libs.firebase.messaging)
}
