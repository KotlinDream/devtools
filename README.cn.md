# Gradle Project Devtools

Kotlin/Java gradle项目的开发辅助工具

[![cn](https://img.shields.io/badge/lang-cn-red.svg)](https://github.com/KotlinDream/devtools/blob/master/README.cn.md)
[![en](https://img.shields.io/badge/lang-en-blue.svg)](https://github.com/KotlinDream/devtools/blob/master/README.md)


## 为什么要有这个项目

Rails 有一堆的开发工具，能帮助我们快速的编写代码。例如Guard, 就能在监听项目的文件，依据变化执行对应的测试。当然他还有很多其他的用法，但是Java/Kotlin项目中没有找到对应的工具。
于是，我自己开发了这个插件。

## 项目目标
1. 开源，免费，永远的免费。
2. 完全使用Kotlin开发，支持 Java/Kotlin 等JVM语言的 Gradle项目。
3. 支持SpringBoot, Quarkus等框架。

## 使用

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

然后在项目目录下运行

```shell
gradle guard
```

这时候，你可以尝试编辑源代码或者对应的测试文件，保存的时候，他就会自动执行对应的测试并输出结果。如果你输入回车，则程序会执行所有的测试。

---
