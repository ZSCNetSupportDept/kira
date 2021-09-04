package love.sola.kira.domain.user

import java.io.Serializable
import javax.persistence.*

@Entity
class UserAuthority(
    @EmbeddedId
    val id: Id,
    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    val user: User
) {
    @Embeddable
    data class Id(val userId: Int, @Enumerated(EnumType.STRING) val authority: Authority) : Serializable

    val authority get() = id.authority

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserAuthority) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
