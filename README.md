# Gradle Project Devtools 

Kotlin/Java, Gradle project development helpers 

[![cn](https://img.shields.io/badge/lang-cn-red.svg)](https://github.com/KotlinDream/devtools/blob/master/README.cn.md)
[![en](https://img.shields.io/badge/lang-en-blue.svg)](https://github.com/KotlinDream/devtools/blob/master/README.md)

## Why should we have this project 

Rails has a bunch of development tools that can help us write code quickly. For example, Guard can monitor the files of the project and execute corresponding tests according to the changes. Of course, it has many other uses, but no corresponding tools have been found in the Java/Kotlin project. 
So, I developed this plugin myself. 

## Project goal 
1. Open source, free, free forever. 
2. Fully developed in Kotlin, supporting Gradle projects in JVM languages such as Java/Kotlin. 
3. Support SpringBoot, Quarkus and other frameworks. 

## Use 

### Add jitpack to settings.gradle.kts 

```kotlin 
// file settings.gradle.kts 

pluginManagement { 
    repositories { 
        maven("https://jitpack.io") 
        gradlePluginPortal() 
    } 
} 
``` 

### Using the plugins DSL 

```kotlin
id("info.dreamcoder.devtools") version "1.3" 
```
Then run

```shell 
gradle guard 
``` 

in the project directory 
At the momment when you edit the source code or corresponding test file, then save , it will automatically execute the corresponding test and output the result. If you press enter, the program will perform all tests.

---



