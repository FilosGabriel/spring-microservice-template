plugins {
    id("java")
    checkstyle
    id("org.sonarqube") version "3.3"

}

java {
    group = "org.filos"
    version = "1.0-SNAPSHOT"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.2")
}
tasks.test {
    useJUnit()
}

subprojects {
    group = "org.filos"
    apply(plugin = "java")
    apply(plugin = "checkstyle")
//    apply(plugin = "org.sonarqube")
    repositories {
        mavenCentral()
    }
    java {
        group = "org.filos"
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
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

    checkstyle {
        toolVersion = "9.0"
        configFile = rootProject.file(".config/checkstyle.xml")
        configProperties["checkstyle.cache.file"] = "${buildDir}/checkstyle.cache"
        isIgnoreFailures = true
        isShowViolations = true
    }

}

//allprojects {
//    group = "org.filos"
//    repositories {
//        mavenCentral()
//    }
//
//    dependencies {
//        //        testing
//        testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
//        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
//
////        lombok
//        compileOnly("org.projectlombok:lombok:1.18.20")
//        annotationProcessor("org.projectlombok:lombok:1.18.20")
//        testCompileOnly("org.projectlombok:lombok:1.18.20")
//        testAnnotationProcessor("org.projectlombok:lombok:1.18.20")
//
//    }
//
//    java {
//        group = "org.filos"
//        sourceCompatibility = org.gradle.api.JavaVersion.VERSION_11
//        targetCompatibility = org.gradle.api.JavaVersion.VERSION_11
//    }
//
//    tasks.test {
//        useJUnit()
//    }
//
//}