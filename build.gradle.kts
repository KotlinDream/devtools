plugins {
    kotlin("jvm") version "1.5.31"
    jacoco
    id("org.sonarqube") version "3.3"
    id("java-gradle-plugin")
    `maven-publish`
}

group = "info.dreamcoder"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://plugins.gradle.org/m2/")
}

dependencies {
    implementation(gradleApi())
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.31")
    implementation("com.github.ajalt.mordant:mordant:2.0.0-beta2")
    implementation("io.github.microutils:kotlin-logging:2.0.11")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2-native-mt")

    implementation("info.dreamcoder:kotby:2a4b5647cd")

    implementation("com.adarshr:gradle-test-logger-plugin:3.0.0")
    implementation("org.junit.jupiter:junit-jupiter:5.8.0")

    testImplementation("org.amshove.kluent:kluent:1.68")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        csv.required.set(true)
    }
}

sonarqube {
    properties {
        property("sonar.projectKey", "KotlinDream_devtools")
        property("sonar.organization", "kotlin-dream")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

gradlePlugin {
    plugins {
        create("migrationPlugin") {
            id = "info.dreamcoder.devtools"
            implementationClass = "gradle.plugins.DevTools"
        }
    }
}