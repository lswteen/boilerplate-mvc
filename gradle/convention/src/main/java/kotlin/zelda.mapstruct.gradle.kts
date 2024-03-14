plugins {
    id("zelda.java")
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.addAll(
            arrayOf(
                    "-Amapstruct.suppressGeneratorTimestamp=true",
                    "-Amapstruct.unmappedTargetPolicy=ERROR",
                    "-Amapstruct.defaultComponentModel=spring",
            ),
    )
}

dependencies {
    implementation("org.mapstruct:mapstruct")
    annotationProcessor("org.mapstruct:mapstruct-processor")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding")
}
