plugins{
    alias(libs.plugins.cityPark.jvm.library)
    alias(libs.plugins.cityPark.dagger.hilt)
}

dependencies{
    api(libs.preferences.datastore.jvm)
    implementation(libs.kotlinx.coroutines.core)
}
