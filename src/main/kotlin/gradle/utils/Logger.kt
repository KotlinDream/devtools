package gradle.utils


import com.github.ajalt.mordant.TermColors
import com.github.ajalt.mordant.TerminalCapabilities
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Logger {
    companion object {
        private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        private val t = TermColors(TermColors.Level.TRUECOLOR)

        fun puts(message: String) {
            println("> ${t.green("Kotby")} $message")
        }

        fun putsWithTime(message: String) {
            val now = LocalDateTime.now().format(dateFormatter)
            puts("${t.green("[$now}]")} $message")
        }

        fun putsError(message: String) {
            println("> ${t.red("Kotby")} $message")
        }

        fun printError(message: String) {
            print(t.red(message))
            System.out.flush()
        }

    }
}
