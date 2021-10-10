package gradle.utils

import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.terminal.Terminal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Logger {
    companion object {
        private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")

        fun puts(message: String) {
            println("> ${green("Kotby")} $message")
        }

        fun putsWithTime(message: String) {
            val now = LocalDateTime.now().format(dateFormatter)
            puts("${green("[$now}]")} $message")
        }

        fun putsError(message: String) {
            println("> ${red("Kotby")} $message")
        }

        fun printError(message: String) {
            print(red(message))
        }

    }
}

