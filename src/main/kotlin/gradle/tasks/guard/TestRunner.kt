package gradle.tasks.guard

import gradle.utils.Logger.Companion.putsWithTime
import mu.KotlinLogging
import org.gradle.api.Project
import org.gradle.tooling.GradleConnector

class TestRunner(private val project: Project) {

    private val logger  = KotlinLogging.logger {}
    private val connection = GradleConnector.newConnector().forProjectDirectory(project.projectDir).connect()

    fun runAllTest() {
        runTest("*")
    }

    fun runTest(className: String) {
        putsWithTime("开始运行项目 [${project.name}] 类 [$className] 的测试 [${project.projectDir}]")

        try {
            connection.newTestLauncher()
                .withJvmTestClasses(className)
                .setStandardOutput(System.out)
                .setStandardError(TaskOut())
                .withArguments("-q")
                .run()
        } catch (e: Exception) {
            logger.info { e.printStackTrace() }
        }

        putsWithTime("完成运行项目 [${project.name}] 类 [$className] 的测试 [${project.projectDir}]")
    }
}
