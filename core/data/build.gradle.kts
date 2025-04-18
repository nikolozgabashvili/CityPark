plugins{
    alias(libs.plugins.cityPark.android.library)
}

android{
    namespace = "ge.tbca.city_park.core.data"
}

dependencies{
    implementation(libs.preferences.datastore)
}