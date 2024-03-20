plugins {
    id("zelda.java")
}

dependencies {

    //h2
    val h2VersionCatalog = "2.2.220"
    runtimeOnly ("com.h2database:h2:$h2VersionCatalog")
    testImplementation ("com.h2database:h2:$h2VersionCatalog")

    implementation ("com.fasterxml.jackson.core:jackson-annotations:2.16.1")
    implementation ("org.springframework.boot:spring-boot-starter-web")

}
