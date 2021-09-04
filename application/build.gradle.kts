plugins {
    id("org.springframework.boot") version "2.5.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

dependencies {
    implementation(libs.spring.starter.base)
    implementation(projects.communication.restController)
}