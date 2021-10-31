plugins {
    kotlin("jvm") version "1.5.31"
    jacoco
    id("org.sonarqube") version "3.3"
    id("java-gradle-plugin")
    `maven-publish`
    id("com.gradle.plugin-publish") version "0.16.0"
}

group = "info.dreamcoder"
version = "1.3.7"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://plugins.gradle.org/m2/")
    mavenLocal()
}

dependencies {
    implementation(gradleApi())
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.31")
    implementation("com.github.ajalt:mordant:1.2.1")
    implementation("io.github.microutils:kotlin-logging:2.0.11")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2-native-mt")

    implementation("info.dreamcoder:kotby:0.5")

    implementation("com.adarshr:gradle-test-logger-plugin:3.0.0")
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")

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

tasks.test {
    useJUnitPlatform()
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
        create("devtoolsPlugin") {
            id = "info.dreamcoder.devtools"
            implementationClass = "gradle.plugins.DevTools"
        }
    }
}

pluginBundle {
    website = "https://github.com/KotlinDream/devtools"
    vcsUrl = "https://github.com/KotlinDream/devtools"


    description = "Development tools for Gradle projects currently include monitoring and automatic execution of test files. When the code file or test file changes, the plug-in automatically detects the corresponding test file and executes it. If you type Enter, all tests will be executed."

    (plugins) {

        "devtoolsPlugin" {
            displayName = "Gradle Project Devtools"
            tags = listOf("individual", "gradle", "dev", "plugin", "devtools", "tools")
            version = project.version.toString()
        }
    }
}
