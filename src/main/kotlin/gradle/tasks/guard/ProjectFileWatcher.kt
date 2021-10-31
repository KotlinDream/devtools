package gradle.tasks.guard

import gradle.utils.ClassFileConversion.Companion.fullTestClassName
import gradle.utils.Logger.Companion.puts
import info.dreamcoder.kotby.io.FileWatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import org.gradle.api.Project
import java.io.File
import java.nio.file.Paths

class ProjectFileWatcher(private val project: Project, private val channel: Channel<String>) {

    private val logger  = KotlinLogging.logger {}
    private val testRunner = TestRunner(project)

    private val fileWatcher = FileWatcher(Paths.get(project.projectDir.path , "/src").toString()).apply {
        onFileModify { fileModifyEvent(it) }
    }

    suspend fun run() = withContext(Dispatchers.IO) {
        puts("开始监听项目 [${project.name}] ")
        fileWatcher.create()
    }

    private fun fileModifyEvent(filePath: String) {
        puts("获取文件变动 [$filePath] ")
        val extensionNames = listOf<String>("kt", "java")
        val fileException = File(filePath).extension

        if(extensionNames.contains(fileException)) {
            val fullTestClassName = fullTestClassName(filePath)
            testRunner.runTest(fullTestClassName)
        }
    }

}