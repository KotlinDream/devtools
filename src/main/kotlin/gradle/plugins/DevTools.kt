package gradle.plugins

import com.adarshr.gradle.testlogger.TestLoggerExtension
import com.adarshr.gradle.testlogger.theme.ThemeType
import gradle.tasks.GuardTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.LogLevel


class DevTools: Plugin<Project> {

    override fun apply(project: Project) {
        setupTestLoggerPlugin(project)
        project.tasks.create("guard", GuardTask::class.java) {
            it.group = "dreamcoder devtools"
        }
    }

    private fun setupTestLoggerPlugin(project: Project) {
        project.allprojects.forEach {
            it.pluginManager.apply("com.adarshr.test-logger")
            it.extensions.configure(TestLoggerExtension::class.java) { testLoggerExtension ->
                testLoggerExtension.theme = ThemeType.MOCHA
                testLoggerExtension.showStackTraces = false
                testLoggerExtension.showSummary = true
                testLoggerExtension.logLevel = LogLevel.QUIET
            }
        }
    }

}