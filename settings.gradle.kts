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
        google()
        mavenCentral()
    }
}

rootProject.name = "DailyQuizAndef"
include(":app")

include(":core")
include(":core:di")
include(":core:navigation")
include(":core:data")
include(":core:design")
include(":core:di:common")
include(":core:di:viewmodel")
include(":core:domain")
include(":core:navigation:graph")
include(":core:navigation:routes")
