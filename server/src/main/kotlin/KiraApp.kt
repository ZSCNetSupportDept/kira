package love.sola.kira

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KiraApp

fun main(args: Array<String>) {
    runApplication<KiraApp>(*args)
}
