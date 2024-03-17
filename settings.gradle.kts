pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

rootProject.name = "boilerplate"

gradle.extra["IS_CI"] = !System.getenv("CI").isNullOrEmpty()
includeBuild("gradle/convention")

include("dependencyManagement")
project(":dependencyManagement").projectDir = File(rootDir, "gradle")
project(":dependencyManagement").buildFileName = "dependencies.gradle.kts"

if (gradle.extra["IS_CI"] == false) {
    include("local-overrides")
    project(":local-overrides").projectDir = File(rootDir, "local/overrides")
    logger.lifecycle("Enabled local-overrides project")
}
include("gradle")
include("gradle:convention")
findProject(":gradle:convention")?.name = "convention"
include("api")
