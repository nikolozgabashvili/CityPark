@file:Suppress("UnstableApiUsage")

include(":feature:fines:domain")


include(":feature:fines")




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

include(":feature:auth:presentation")
include(":feature:auth:domain")
include(":feature:auth:data")

include(":core:designsystem")
include(":core:domain")
include(":core:data")
include(":core:ui")

include(":datastore:domain")
include(":datastore:data")

include(":feature:settings:presentation")
include(":feature:settings:domain")
include(":feature:settings:data")

include(":feature:payment:presentation")
include(":feature:payment:domain")
include(":feature:payment:data")

include(":feature:cars:presentation")
include(":feature:cars:domain")
include(":feature:cars:data")

include(":feature:home:presentation")

include(":feature:reservation:presentation")
include(":feature:reservation:domain")
include(":feature:reservation:data")

include(":feature:messaging:presentation")
include(":feature:messaging:domain")
include(":feature:messaging:data")

include(":feature:user:domain")
include(":feature:user:data")

include(":feature:parking:presentation")
include(":feature:parking:domain")
include(":feature:parking:data")


include(":feature:more:presentation")
include(":feature:more")
include(":feature:user:presentation")