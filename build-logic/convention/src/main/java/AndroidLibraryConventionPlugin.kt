import com.android.build.gradle.LibraryExtension
import ge.tbca.convention.ExtensionType
import ge.tbca.convention.configureBuildTypes
import ge.tbca.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {

            pluginManager.run {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                configureBuildTypes(this,ExtensionType.LIBRARY)
            }

        }
    }
}