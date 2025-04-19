@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "cityPark"
include(":app")
include(":feature:auth:domain")
include(":feature:auth:data")
include(":feature")
include(":core:designsystem")
include(":core:domain")
include(":core:data")
include(":core:ui")
include(":feature:auth:presentation")

include(":datastore:domain")
include(":datastore:data")
include(":datastore")
include(":feature:settings:presentation")
include(":feature:settings:domain")
include(":feature:settings:data")
