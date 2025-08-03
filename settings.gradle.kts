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

//app
include(":app")

//core
include(":core:data")

include(":core:design")

include(":core:di:common")
include(":core:di:viewmodel")

include(":core:domain")

include(":core:navigation:graph")
include(":core:navigation:routes")

//feature
include(":feature:start:presentation")

include(":feature:quiz:presentation")
include(":feature:quiz:di")
include(":feature:quiz:data")
include(":feature:quiz:domain")

include(":feature:history:presentation")
include(":feature:history:di")
include(":feature:history:domain")
include(":core:utils")
