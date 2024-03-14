/**
 * Spring Framework, Spring Boot에 의존하는 프로젝트에 대한 설정
 *
 * 라이브러리, 어플리케이션에 따라 commerce.spring-boot-library, commerce.spring-boot-application 플러그인을 사용할 것
 */

plugins {
    id("zelda.java")
}

tasks.withType<AbstractArchiveTask>().configureEach {
    // https://youtrack.jetbrains.com/issue/IDEA-305759/Gradle-cannot-handle-classpath.index-duplicates
    exclude("classpath.index")
}

tasks.withType<ProcessResources>().configureEach {
    // 동일한 파일(main/resources/application.yaml, intTest/resources/application.yaml)이 있어서, 리소스 복사할 때 충돌 회피
    // Execution failed for task ':web:processIntTestResources'.
    // > Entry application.yaml is a duplicate but no duplicate handling strategy has been set.
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

dependencies {
    annotationProcessor("org.springframework:spring-context-indexer")
    annotationProcessor("org.springframework.boot:spring-boot-autoconfigure-processor")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    compileOnly("org.springframework:spring-context")
    compileOnly("org.springframework:spring-beans")
    compileOnly("org.springframework.boot:spring-boot-autoconfigure")

    compileOnly("jakarta.validation:jakarta.validation-api")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

configurations.all {
    exclude("org.springframework", "spring-oxm") // No XML
}

if (gradle.extra["IS_CI"] == false) {
    with(project) {
        if (project.path == ":local-overrides") {
            // Prevent circular dependency
            return@with
        }

        // dependencyManagement 프로젝트 자체처럼 외부에 publish시 노출되지 않아야 함
        val localOverrides by configurations.creating {
            isVisible = false
        }
        configurations.configureEach {
            if (name.matches(Regex("\\w*[Rr]untimeClasspath$")) && isCanBeResolved && !isCanBeConsumed) {
                extendsFrom(localOverrides)
            }
        }
        dependencies {
            localOverrides(project(":local-overrides")) {
                isTransitive = false
            }
        }
        afterEvaluate {
            tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar>().configureEach {
                classpath -= localOverrides
            }
        }
    }
}
