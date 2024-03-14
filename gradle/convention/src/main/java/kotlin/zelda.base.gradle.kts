/**
 * Gradle "base" 플러그인에 대한 설정
 *
 * @see org.gradle.api.plugins.BasePlugin
 * @see <a href="https://docs.gradle.org/current/userguide/base_plugin.html">Base plugin reference</a>
 */

plugins {
    base
    id("zelda.dependency-management")
}

@Suppress("ktlint:standard:property-naming")
val DEFAULT_PROJECT_GROUP = "com.moin.zelda"
project.group = DEFAULT_PROJECT_GROUP

//region 한 서브프로젝트에서 :mother:child, :father:child 두개의 서브프로젝트를 의존시 발생하는 이슈 처리

// 이 프로젝트까지의 모듈 경로
// ex) :grandmother:mother:child -> "grandmother:mother"
// ex) :application -> ""
val projectPathBasename = project.path.replaceAfterLast(':', "").trim(':')

if (projectPathBasename.isNotEmpty()) {
    // 여기도 archiveBaseName와 마찬가지로 child 서브프로젝트명이 겹치면 의존성에서 난해한 오류가 발생
    project.group = "$DEFAULT_PROJECT_GROUP.$projectPathBasename"
}

tasks.withType<AbstractArchiveTask>().configureEach {
    // :mother:child, :father:child 두개의 모듈이 있는 경우 둘다 child.jar 로 모호하게 생성되어 Spring Boot Fat Jar 내에서 덮어쓰게 됨
    // mother-child-SUFFIX.jar 형태로 생성되도록 함
    archiveBaseName.set(project.path.trimStart(':').replace(':', '-'))
}

//endregion

tasks.withType<AbstractArchiveTask>().configureEach {
    duplicatesStrategy = DuplicatesStrategy.FAIL

    // Reproducible build
    isPreserveFileTimestamps = false
    isReproducibleFileOrder = true
    dirMode = Integer.parseInt("0755", 8)
    fileMode = Integer.parseInt("0644", 8)
}
