package gradle.tasks.guard

import gradle.utils.Logger.Companion.printError
import java.io.OutputStream

class TaskOut : OutputStream() {
    private val buffer = StringBuffer()

    override fun write(b: Int) {
        buffer.append(b.toChar())
    }

    override fun flush() {
        printError(buffer.toString())
        buffer.setLength(0)
    }

}