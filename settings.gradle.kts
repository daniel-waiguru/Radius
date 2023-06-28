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
include(":core:database")
//feature facilities
include(
    ":features:facilities:data",
    ":features:facilities:domain",
    ":features:facilities:presentation"
)
include(":core:network")
include(":core:work")
include(":core:common")
