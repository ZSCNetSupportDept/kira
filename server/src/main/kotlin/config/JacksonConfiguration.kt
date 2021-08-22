package love.sola.kira.config

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.zalando.problem.ProblemModule
import org.zalando.problem.violations.ConstraintViolationProblemModule

@Configuration
class JacksonConfiguration {

    @Bean
    fun parameterNamesModule() = ParameterNamesModule(JsonCreator.Mode.DEFAULT)

    @Bean
    fun javaTimeModule() = JavaTimeModule()

    @Bean
    fun jdk8Module() = Jdk8Module()

    @Bean
    fun kotlinModule() = KotlinModule()

    @Bean
    fun hibernate5Module() = Hibernate5Module()

    @Bean
    fun problemModule() = ProblemModule()

    @Bean
    fun constraintViolationProblemModule() = ConstraintViolationProblemModule()
}
