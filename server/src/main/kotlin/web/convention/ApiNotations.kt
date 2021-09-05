package love.sola.kira.web.convention

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirements
import org.springframework.security.access.prepost.PreAuthorize
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FUNCTION

@Operation(deprecated = true, description = "### Internal API: Not for code use.")
@PreAuthorize("hasRole('SYS_ADMIN')")
@Target(FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ForInternalUse

@Operation(deprecated = true, description = "### Work In Progress: Functionality is not yet complete.")
@Target(FUNCTION, CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class WorkInProgress

@Operation(description = "### Experimental: Please use this API with caution.")
@Target(FUNCTION, CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Experimental

@SecurityRequirements
@PreAuthorize("permitAll()")
@Target(FUNCTION, CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class NoLoginRequired

@PreAuthorize("hasRole('USER')")
@Target(FUNCTION, CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class RequiresLogin

@PreAuthorize("hasRole('ADMIN')")
@Target(FUNCTION, CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class RequiresAdmin
