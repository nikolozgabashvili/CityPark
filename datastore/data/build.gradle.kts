plugins{
    alias(libs.plugins.cityPark.android.library)
    alias(libs.plugins.cityPark.dagger.hilt)
}

android{
    namespace= "ge.tbca.city_park.datastore.data"
}

dependencies{
    implementation(libs.preferences.datastore)

    implementation(projects.datastore.domain)
}
