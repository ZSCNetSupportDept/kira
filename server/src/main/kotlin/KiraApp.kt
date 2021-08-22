package love.sola.kira

import love.sola.kira.config.ApplicationProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(ApplicationProperties::class)
@SpringBootApplication
class KiraApp

fun main(args: Array<String>) {
    runApplication<KiraApp>(*args)
}
