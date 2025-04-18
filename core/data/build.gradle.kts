plugins{
    alias(libs.plugins.cityPark.android.library)
    alias(libs.plugins.cityPark.dagger.hilt)
}

android{
    namespace = "ge.tbca.city_park.core.data"
}

dependencies{
    implementation(libs.preferences.datastore)
}