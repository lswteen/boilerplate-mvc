plugins {
    id("zelda.spring-boot-library")
    id("zelda.mapstruct")
}

dependencies {
    //querydsl
    api("com.querydsl", "querydsl-jpa", classifier = "jakarta")
    api("com.querydsl:querydsl-sql")

    //jpa
    implementation("org.springframework.data:spring-data-commons")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")


    implementation ("javax.annotation:javax.annotation-api:1.3.2")

    // 추가된 Apache Commons Codec 의존성
    implementation ("commons-codec:commons-codec:1.16.1")

    implementation(project(":common"))
}
