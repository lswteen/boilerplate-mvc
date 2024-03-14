plugins {
    id("zelda.java")
}

dependencies {
    compileOnly("jakarta.persistence:jakarta.persistence-api")

    annotationProcessor("jakarta.persistence:jakarta.persistence-api")
    annotationProcessor("com.querydsl", "querydsl-apt", classifier = "jakarta")

    compileOnly("org.hibernate.orm:hibernate-core")
    compileOnly("org.hibernate.orm:hibernate-envers")

    // @Table, @Entity
    annotationProcessor("org.springframework:spring-context-indexer")
}
