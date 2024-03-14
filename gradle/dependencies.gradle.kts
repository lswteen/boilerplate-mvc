plugins {
    `java-platform`
    id("zelda.repositories")
}

javaPlatform {
    allowDependencies()
}

dependencies {
    for (bomDependency in libs.bundles.bomDependencies.get()) {
        api(platform(bomDependency))
    }

    api(platform(libs.springCloudAws.bom)) {
        exclude(group = "software.amazon.awssdk")
        exclude(group = "software.amazon.awssdk.crt")
    }

    constraints {
        api(libs.bundles.dependencies)
    }
}

//sonar.isSkipProject = true
