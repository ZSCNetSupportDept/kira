package love.sola.kira.config

import org.springdoc.core.Constants.SPRINGDOC_ENABLED
import org.springdoc.core.customizers.OperationCustomizer
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnProperty(name = [SPRINGDOC_ENABLED], matchIfMissing = true)
class OpenApiConfiguration {

    /**
     * Generate more reasonable operationId and make the method name as summary by default.
     */
    @Bean
    fun operationCustomizer() = OperationCustomizer { operation, handlerMethod ->
        operation.operationId = handlerMethod.beanType.simpleName + "#" + handlerMethod.method.name
        if (operation.summary == null) {
            operation.summary = handlerMethod.method.name
        }
        operation
    }
}
