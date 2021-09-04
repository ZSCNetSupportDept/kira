package love.sola.kira.repository.user

import love.sola.kira.domain.user.UserPassword
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface UserPasswordRepository : JpaRepository<UserPassword, Int>, JpaSpecificationExecutor<UserPassword>
