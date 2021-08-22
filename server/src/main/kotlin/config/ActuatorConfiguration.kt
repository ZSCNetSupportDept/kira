package love.sola.kira.config

import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("http-trace")
class ActuatorConfiguration {

    @Bean
    fun httpTraceRepository() = InMemoryHttpTraceRepository()
}
