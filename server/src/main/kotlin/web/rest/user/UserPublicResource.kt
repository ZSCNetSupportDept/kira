package love.sola.kira.web.rest.user

import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import love.sola.kira.web.convention.NoLoginRequired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
@Tag(name = "User")
class UserPublicResource {

    data class PrincipalResponse(val user: Any?, val csrf: CsrfToken)

    @NoLoginRequired
    @GetMapping("whoami")
    fun whoami(@AuthenticationPrincipal _principal: Any?, @Parameter(hidden = true) _csrf: CsrfToken) =
        PrincipalResponse(_principal, _csrf)
}
