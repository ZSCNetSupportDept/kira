package love.sola.kira.security

import com.fasterxml.jackson.annotation.JsonIgnore
import love.sola.kira.domain.user.Authority
import love.sola.kira.domain.user.User
import love.sola.kira.domain.user.UserPassword
import org.springframework.security.core.CredentialsContainer
import org.springframework.security.core.userdetails.UserDetails

data class UserPrincipal(
    val id: Int,
    val login: String,
    private val username: String,
    val active: Boolean,
    @JsonIgnore
    private var password: String?,
    private val authorities: List<Authority>
) : UserDetails, CredentialsContainer {

    constructor(user: User, password: UserPassword?, authorities: List<Authority>) : this(
        user.id,
        user.login,
        user.username,
        user.active,
        password?.password,
        authorities
    )

    override fun getAuthorities(): List<Authority> = authorities

    @JsonIgnore
    override fun getPassword(): String? = password

    override fun eraseCredentials() {
        password = null
    }

    override fun getUsername(): String = login

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = active

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = active
}
