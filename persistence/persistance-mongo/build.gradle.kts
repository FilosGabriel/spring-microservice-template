plugins {
    id("org.springframework.boot") version "2.5.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

java{
    version = "0.0.1-SNAPSHOT"
}

extra["testcontainersVersion"] = "1.15.3"


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:mongodb")
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
    }
}
