package love.sola.kira.repository.user

import love.sola.kira.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface UserRepository : JpaRepository<User, Int>, JpaSpecificationExecutor<User> {
    fun findByLogin(login: String): User?
}
