plugins {
    id("org.springframework.boot") version "2.5.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

dependencies {
    implementation(projects.shared.web.dto)
    implementation(projects.shared.web.exceptions)
    implementation(projects.bussiness)
    implementation(libs.spring.starter.web)
}