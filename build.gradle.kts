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
    testImplementation(libs.testing.junit.api)
    testRuntimeOnly(libs.testing.junit.engine)
}
tasks.test {
    useJUnit()
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "checkstyle")
//    apply(plugin = "org.sonarqube")
    repositories {
        mavenCentral()
    }
    java {
        group = "org.filos"
        sourceCompatibility = JavaVersion.VERSION_16
        targetCompatibility = JavaVersion.VERSION_16
    }

    dependencies {
        lombok("org.projectlombok:lombok:1.18.20")

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


fun org.gradle.api.artifacts.dsl.DependencyHandler.lombok(name: String) {
    compileOnly(name)
    annotationProcessor(name)
    testCompileOnly(name)
    testAnnotationProcessor(name)
}
