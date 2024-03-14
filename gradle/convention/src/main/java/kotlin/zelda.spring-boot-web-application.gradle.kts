plugins {
    id("zelda.spring-boot-application")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework:spring-webmvc")
    compileOnly("jakarta.servlet:jakarta.servlet-api")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
