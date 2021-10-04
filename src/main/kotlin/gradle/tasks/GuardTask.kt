package gradle.tasks

import gradle.tasks.guard.Console
import gradle.tasks.guard.ProjectFileWatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction


open class GuardTask : DefaultTask(){
    private val channel = Channel<String>(UNLIMITED)

    @TaskAction
    suspend fun run() {
        startAllProjectWatch()
        startConsoleWatch()
    }

    private suspend fun startAllProjectWatch() {
        coroutineScope {
            launch {
                ProjectFileWatcher(project, channel).run()
            }
            project.subprojects.forEach{
                launch {
                    ProjectFileWatcher(it, channel).run()
                }
            }
        }

        project.subprojects.forEach{
            ProjectFileWatcher(it, channel).run()
        }
    }

    private fun startConsoleWatch() {
        Console(project, channel).start()
    }

}
