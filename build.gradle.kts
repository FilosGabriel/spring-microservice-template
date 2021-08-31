plugins {
    id("java")
}

java {
    group = "org.filos"
    version = "1.0-SNAPSHOT"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}
tasks.test {
    useJUnit()
}

subprojects {
    group = "org.filos"
    apply(plugin = "java")
    repositories {
        mavenCentral()
    }

}

allprojects {
    group = "org.filos"
    repositories {
        mavenCentral()
    }

    dependencies {
        //        testing
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")

//        lombok
        compileOnly("org.projectlombok:lombok:1.18.20")
        annotationProcessor("org.projectlombok:lombok:1.18.20")
        testCompileOnly("org.projectlombok:lombok:1.18.20")
        testAnnotationProcessor("org.projectlombok:lombok:1.18.20")

    }
    tasks.test {
        useJUnit()
    }

}