plugins {
    `kotlin-dsl`
}

group = "ge.tbca.buildlogic"

dependencies {
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {

    plugins {
        register("androidApplication") {
            id = "ge.tbca.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidComposeApplication") {
            id = "ge.tbca.android.compose.application"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "ge.tbca.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "ge.tbca.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("daggerHilt") {
            id = "ge.tbca.dagger.hilt"
            implementationClass = "DaggerHiltConventionPlugin"
        }

        register("androidFeature") {
            id = "ge.tbca.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("jvmLibrary") {
            id = "ge.tbca.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
    }
}