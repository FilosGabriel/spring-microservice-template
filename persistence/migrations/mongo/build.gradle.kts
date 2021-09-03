plugins {
    java
}

group = "org.filos"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("com.github.cloudyrock.mongock:mongock-bom:4.3.8"))
    implementation("com.github.cloudyrock.mongock:mongock-bom:4.3.8")
    implementation("com.github.cloudyrock.mongock:mongock-standalone:4.3.8")
    implementation("com.github.cloudyrock.mongock:mongodb-sync-v4-driver:4.3.8")
    implementation("org.mongodb:mongodb-driver-sync:4.3.1")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}