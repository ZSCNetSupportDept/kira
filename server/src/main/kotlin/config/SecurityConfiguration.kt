package love.sola.kira.config

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.annotations.security.SecuritySchemes
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import org.springframework.security.web.csrf.CookieCsrfTokenRepository
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport
import javax.servlet.http.HttpServletResponse.SC_OK
import javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED

@SecuritySchemes(
    SecurityScheme(
        name = "BasicAuth",
        description = "NOTE: You don't have to do this to use the _Try it out_ feature!" +
                " I put this in here just to indicate there are two ways to authenticate.",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
    ),
    SecurityScheme(
        name = "HttpSession",
        description = "Again, you don't have to do this because you've already authenticated to see this page.",
        type = SecuritySchemeType.APIKEY,
        `in` = SecuritySchemeIn.COOKIE,
        paramName = "JSESSIONID"
    )
)
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import(SecurityProblemSupport::class)
class SecurityConfiguration(
    private val applicationProperties: ApplicationProperties,
    private val problemSupport: SecurityProblemSupport,
    private val userDetailsService: UserDetailsService
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http {
            csrf {
                csrfTokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse()
            }
            httpBasic {}
            formLogin {
                loginProcessingUrl = "/api/auth/login"
                authenticationSuccessHandler =
                    AuthenticationSuccessHandler { _, response, _ -> response.status = SC_OK }
                authenticationFailureHandler =
                    AuthenticationFailureHandler { _, response, _ -> response.sendError(SC_UNAUTHORIZED) }
            }
            rememberMe {
                key = applicationProperties.security.rememberMe.key
                userDetailsService = this@SecurityConfiguration.userDetailsService
            }
            logout {
                logoutUrl = "/api/auth/logout"
                logoutSuccessHandler = LogoutSuccessHandler { _, response, _ -> response.status = SC_OK }
            }
            authorizeRequests {
                authorize(EndpointRequest.toAnyEndpoint(), hasRole("SYS_ADMIN"))
                authorize("/api/api-docs/*", hasRole("SYS_ADMIN"))
                authorize("/api/swagger-ui.html", hasRole("SYS_ADMIN"))
                authorize("/api/swagger-ui/index.html", hasRole("SYS_ADMIN"))
            }
            exceptionHandling {
                authenticationEntryPoint = problemSupport
                accessDeniedHandler = problemSupport
            }
        }
    }
}

/**
 * THIS IS REQUIRED to exclude from [SecurityConfiguration] to avoid
 * the circular dependency situation introduced by [UserDetailsServiceAutoConfiguration]
 */
@Configuration
class PasswordEncoderConfiguration {
    @Bean
    fun passwordEncoder(): PasswordEncoder = createPasswordEncoder()

    private fun createPasswordEncoder(): PasswordEncoder {
        val passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder() as DelegatingPasswordEncoder
        passwordEncoder.setDefaultPasswordEncoderForMatches(RejectingPasswordEncoder)
        return passwordEncoder
    }

    object RejectingPasswordEncoder : PasswordEncoder {
        override fun encode(rawPassword: CharSequence): String {
            throw UnsupportedOperationException("encode is not supported")
        }

        override fun matches(rawPassword: CharSequence?, encodedPassword: String?): Boolean = false
    }
}
