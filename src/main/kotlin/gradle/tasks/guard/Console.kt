package gradle.tasks.guard

import gradle.utils.Logger.Companion.puts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.withContext
import org.gradle.api.Project

class Console(private val project: Project, private val channel: Channel<String>) {
    suspend fun start() = withContext(Dispatchers.IO) {
        puts("开始监听键盘输入" )
        while(true) {
            when(readLine().toString().trim()) {
                "" -> runAllTest()
            }
        }
    }

    private fun runAllTest() {
        puts("运行所有测试")
        TestRunner(project).runAllTest()
    }
}


