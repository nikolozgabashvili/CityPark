import com.android.build.api.dsl.ApplicationExtension
import ge.tbca.convention.AndroidProject
import ge.tbca.convention.AndroidProject.TARGET_SDK
import ge.tbca.convention.ExtensionType
import ge.tbca.convention.configureBuildTypes
import ge.tbca.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                defaultConfig {
                    applicationId = AndroidProject.APPLICATION_ID
                    versionCode = AndroidProject.VERSION_CODE
                    versionName = AndroidProject.VERSION_NAME
                    targetSdk = TARGET_SDK
                }

                configureKotlinAndroid(this)

                configureBuildTypes(this,ExtensionType.APPLICATION)
            }

        }
    }
}