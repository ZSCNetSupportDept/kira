package love.sola.kira.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

/**
 * @property baseUrl base URL without trailing slash and any prefixes (`/api`, `/upload` etc)
 */
@ConstructorBinding
@ConfigurationProperties(prefix = "application")
data class ApplicationProperties(
    val baseUrl: String,
    val security: Security
) {

    init {
        require(!baseUrl.endsWith('/')) { "baseUrl must not end with trailing slash." }
    }

    data class Security(val rememberMe: RememberMe, val headers: Headers) {
        data class RememberMe(val key: String)
        data class Headers(val contentSecurityPolicy: String, val permissionsPolicy: String)
    }
}
