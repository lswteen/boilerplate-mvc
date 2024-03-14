plugins {
    `kotlin-dsl` // Kotlin DSL 플러그인을 적용합니다.
}

repositories {
    gradlePluginPortal() // Gradle Plugin Portal을 리포지토리로 추가합니다.
}

dependencies {
    implementation(gradleApi())
    implementation(libs.gradlePlugin.springBoot)
}
