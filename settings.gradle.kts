pluginManagement {
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
        maven(url="https://jitpack.io")
        mavenCentral()
        google()
    }
}

rootProject.name = "Pokedex"
include(":core:common")
include(":core:component")
include(":core:base")
include(":core:model")
include(":core:data")
include(":core:database")
include(":core:network")
include(":core:repository")
include(":feature:main")
include(":feature:detail")
include(":app")
