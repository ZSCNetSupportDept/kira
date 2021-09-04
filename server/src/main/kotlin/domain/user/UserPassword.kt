package love.sola.kira.domain.user

import love.sola.kira.domain.convention.DatedEntity
import java.time.Instant
import javax.persistence.*
import javax.persistence.FetchType.LAZY

/**
 * We store user's password separately because not all users are authenticated via password.
 * Also with this approach we can safely serialize the [User] without any worries.
 */
@Entity
class UserPassword(
    @Id
    val id: Int,

    @MapsId
    @OneToOne(fetch = LAZY, optional = false)
    val user: User,

    @Column(nullable = false)
    var password: String,

    createdAt: Instant = Instant.now(),
    lastModifiedAt: Instant = Instant.now()
) : DatedEntity(createdAt, lastModifiedAt) {
    constructor(user: User, password: String) : this(user.id, user, password)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserPassword) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}
