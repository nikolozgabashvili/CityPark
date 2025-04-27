plugins {
    alias(libs.plugins.cityPark.android.feature)
    alias(libs.plugins.cityPark.dagger.hilt)
}

android {
    namespace = "ge.tbca.city_park.messaging.presentation"
}


dependencies {
    implementation(libs.firebase.messaging)
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.feature.messaging.domain)
    implementation(projects.feature.user.domain)
}
