plugins {
    id("java")
}


repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(libs.mongock.bom))
    implementation(libs.bundles.mongock)
    implementation("com.github.javafaker:javafaker:0.12")
}

