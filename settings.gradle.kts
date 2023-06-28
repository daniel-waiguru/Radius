pluginManagement {
    repositories {
        google()
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

rootProject.name = "Radius"
include(":app")
include(
    ":core:database",
    ":core:network",
    ":core:work",
    ":core:common",
    ":core:models"
)
//feature facilities
include(
    ":features:facilities:data",
    ":features:facilities:domain",
    ":features:facilities:presentation"
)
include(":core:ui")
