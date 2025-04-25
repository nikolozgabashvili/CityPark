plugins{
    alias(libs.plugins.cityPark.jvm.library)
    alias(libs.plugins.cityPark.dagger.hilt)
}


dependencies{
    implementation(libs.kotlinx.coroutines.core)
}


dependencies{
    implementation(projects.core.domain)
}
