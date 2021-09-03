plugins {
    id("java")
    id("org.springframework.boot") version "2.5.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

java {
    version = "0.0.1-SNAPSHOT"
}

extra["testcontainersVersion"] = "1.15.3"


dependencies {
    implementation(libs.spring.data.mongo)
    testImplementation(libs.spring.test)
    testImplementation(libs.testing.testcontainers.junit)
    testImplementation(libs.testing.testcontainers.mongo)
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
    }
}
