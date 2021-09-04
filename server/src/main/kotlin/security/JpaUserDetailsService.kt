package love.sola.kira.security

import love.sola.kira.domain.user.Authority
import love.sola.kira.domain.user.User
import love.sola.kira.domain.user.UserAuthority
import love.sola.kira.repository.user.UserAuthorityRepository
import love.sola.kira.repository.user.UserPasswordRepository
import love.sola.kira.repository.user.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Authenticate a user from the database.
 *
 * NOTE: DO NOT autowire this bean type directly unless you know what you are doing!!!
 * Doing so could result breaking tests as we use [InMemoryUserDetailsManager] for layered unit tests instead.
 */
@Deprecated(
    "UserDetailsService interface should be used instead of specific implementation.",
    replaceWith = ReplaceWith("UserDetailsService")
)
@Service
class JpaUserDetailsService(
    private val userRepository: UserRepository,
    private val userPasswordRepository: UserPasswordRepository,
    private val userAuthorityRepository: UserAuthorityRepository
) : UserDetailsService {

    @Transactional(readOnly = true)
    override fun loadUserByUsername(login: String): UserDetails {
        val user = userRepository.findByLogin(login)
            ?: throw UsernameNotFoundException("User $login does not exist")
        val password = userPasswordRepository.findByIdOrNull(user.id)
        val authorities = createAuthoritiesFor(user)
        return UserPrincipal(user, password, authorities)
    }

    private fun createAuthoritiesFor(user: User): List<Authority> {
        val authorities = mutableListOf(Authority.ROLE_USER)
        val additionalAuthorities = userAuthorityRepository.findByUser(user).map(UserAuthority::authority)
        authorities += additionalAuthorities
        return authorities.distinct().sorted()
    }
}
