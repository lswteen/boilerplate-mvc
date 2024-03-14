import org.springframework.boot.gradle.dsl.SpringBootExtension

plugins {
    id("zelda.spring-boot")
    id("org.springframework.boot")
}

configure<SpringBootExtension> {
    buildInfo()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-json")

    // @Transactional
    compileOnly("org.springframework:spring-tx")

    // warning: unknown enum constant GenerationType.IDENTITY
    //  reason: class file for jakarta.persistence.GenerationType not found
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")

    // warning: unknown enum constant RequiredMode.REQUIRED
    //  reason: class file for io.swagger.v3.oas.annotations.media.Schema$RequiredMode not found
    annotationProcessor("io.swagger.core.v3:swagger-annotations-jakarta")
}
