package love.sola.kira.domain.user

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class User(
    @Id
    val id: Int,
    @Column(nullable = false)
    val login: String,
    @Column(nullable = false)
    val username: String,
    var avatarUri: String? = null,
    @Column(nullable = false)
    var active: Boolean = true
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}
