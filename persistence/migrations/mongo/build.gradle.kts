plugins {
    java
}

group = "org.filos"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(libs.mongock.bom))
    implementation(libs.bundles.mongock)

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}