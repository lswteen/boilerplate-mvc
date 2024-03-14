import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.internal.deprecation.DeprecatableConfiguration

plugins {
    id("zelda.java-base")
    java
}


java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17

    // runtimeClasspath 를 compileClasspath와 동일하게 지정
    consistentResolution {
        useCompileClasspathVersions()
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"

    options.compilerArgs.add("-parameters")
}

configurations.all {
    resolutionStrategy {

        // prefer modules that are part of this build (multi-project or composite build) over external modules
        preferProjectModules()

        // -SNAPSHOT
        cacheChangingModulesFor(60, TimeUnit.SECONDS)
    }
}

configurations {
    val annotationProcessor by configurations
    val compileOnly by configurations

    compileOnly {
        extendsFrom(annotationProcessor)
    }
    testCompileOnly {
        extendsFrom(compileOnly)
    }
    testAnnotationProcessor {
        extendsFrom(annotationProcessor)
    }
    testRuntimeClasspath {
        shouldResolveConsistentlyWith(configurations["runtimeClasspath"])
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()

    jvmArgs = listOf(
            "-XX:+AlwaysPreTouch",
            "-XX:+UseZGC", "-XX:ZCollectionInterval=30", "-XX:+ZUncommit", "-XX:ZUncommitDelay=5",
            //"-XX:MinHeapSize=1g", "-XX:MaxRAMPercentage=50.0", // 자체 -Xmx512m과 충돌
    )

    maxHeapSize = "4g"

    testLogging {
        events(TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED)
        showStandardStreams = true
        exceptionFormat = TestExceptionFormat.FULL
    }
}

dependencies {
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    annotationProcessor("com.google.code.findbugs:jsr305:3.0.2")

    compileOnly("jakarta.annotation:jakarta.annotation-api")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    testImplementation("org.assertj:assertj-core")

    testImplementation("org.mockito:mockito-core")
    testImplementation("org.mockito:mockito-junit-jupiter")
}

configurations.all {
    exclude("junit", "junit")
}

// https://stackoverflow.com/a/38528497
fun Configuration.isDeprecated() = this is DeprecatableConfiguration && this.isDeprecatedForResolution && !this.canSafelyBeResolved()

fun ConfigurationContainer.resolveAll() = this
        .matching { it.isCanBeResolved && !it.isDeprecated() }
        .forEach {
            logger.debug("Resolving ${it.name}...")
            it.resolve()
        }

tasks.register("downloadDependencies") {
    description = "프로젝트 내의 모든 의존성 다운로드. 캐시 갱신용으로 사용"
    doLast {
        configurations.resolveAll()
        buildscript.configurations.resolveAll()
    }
}
