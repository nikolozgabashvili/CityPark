plugins{
    alias(libs.plugins.cityPark.jvm.library)
    alias(libs.plugins.cityPark.dagger.hilt)
}

dependencies{
    implementation(libs.kotlinx.coroutines.core)
    implementation(projects.datastore.domain)
}