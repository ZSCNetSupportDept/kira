package love.sola.kira.repository.user

import love.sola.kira.domain.user.User
import love.sola.kira.domain.user.UserAuthority
import org.springframework.data.jpa.repository.JpaRepository

interface UserAuthorityRepository : JpaRepository<UserAuthority, UserAuthority.Id> {
    fun findByUser(user: User): List<UserAuthority>
}
