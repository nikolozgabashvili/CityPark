package ge.tbca.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureBuildTypes(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    extensions.configure<ApplicationExtension> {
        buildTypes {
            release {
                isMinifyEnabled = true
                proguardFiles(
                    commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
    }

}