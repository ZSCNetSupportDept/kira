package love.sola.kira.domain.user

import org.springframework.security.core.GrantedAuthority

enum class Authority : GrantedAuthority {
    ROLE_USER,
    ROLE_STAFF,
    ROLE_MODERATOR,
    ROLE_ADMIN,
    ROLE_SYS_ADMIN,
    ;

    override fun getAuthority(): String = name
}
