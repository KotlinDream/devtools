package gradle.tasks

import gradle.tasks.guard.Console
import gradle.tasks.guard.ProjectFileWatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction


open class GuardTask : DefaultTask(){
    private val channel = Channel<String>(UNLIMITED)

    @TaskAction
    fun run() = runBlocking {
        startAllProjectWatch(this)
        startConsoleWatch(this)
    }

    private fun startAllProjectWatch(scope: CoroutineScope) {
        scope.launch {
            ProjectFileWatcher(project, channel).run()
        }
    }

    private fun startConsoleWatch(scope: CoroutineScope) {
        scope.launch {
            Console(project, channel).start()
        }
    }

}
