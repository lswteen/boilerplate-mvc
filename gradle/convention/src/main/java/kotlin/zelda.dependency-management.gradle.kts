/**
 * 프로젝트 전체에 일관된 의존성 버전을 관리
 *
 * <p>실제 버전 지정은 ":dependencyManagement" 프로젝트 (<code>/gradle/dependencies.gradle.kts</code>) 에서 관리</p>
 * <p>이 플러그인은 dependencyManagement 사용을 강제하고, 모듈 대체 설정만을 관리</p>
 */

plugins {
    base
}

//region 의존성 버전 관리

evaluationDependsOn(":dependencyManagement")
val dependencyManagement by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = false
    isVisible = false
}

afterEvaluate {
    configurations.configureEach {
        if (isCanBeResolved && !isCanBeConsumed) {
            extendsFrom(dependencyManagement)
            logger.info("Applied dependencyManagement to {}", name)
        }
    }
}

dependencies {
    dependencyManagement(enforcedPlatform(project(":dependencyManagement")))
}

//endregion

// region 중복 의존성 대체

val dependencySubstitutions = mapOf(
        "javax.annotation:javax.annotation-api" to "jakarta.annotation:jakarta.annotation-api",
        "javax.transaction:javax.transaction-api" to "jakarta.transaction:jakarta.transaction-api",
        "javax.persistence:javax.persistence-api" to "jakarta.persistence:jakarta.persistence-api",
        "javax.activation:javax.activation-api" to "jakarta.activation:jakarta.activation-api",
        "javax.servlet:javax.servlet-api" to "jakarta.servlet:jakarta.servlet-api",
        "com.vaadin.external.google:android-json" to "org.json:json",
        "org.jboss.spec.javax.transaction:jboss-transaction-api_1.2_spec" to "jakarta.transaction:jakarta.transaction-api",
)

afterEvaluate {
    val disableSubstitutions = (project.findProperty("commerce.dependency-management.disableSubstitutions") as Set<*>?)
            ?: emptySet<String>()
    dependencies {
        modules {
            for ((module, replacement) in dependencySubstitutions) {
                if (disableSubstitutions.contains(module)) {
                    continue
                }
                module(module) {
                    replacedBy(replacement)
                }
            }
        }
    }
}

// endregion
