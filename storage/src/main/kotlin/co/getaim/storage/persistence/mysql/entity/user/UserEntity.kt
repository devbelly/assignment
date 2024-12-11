package co.getaim.storage.persistence.mysql.entity.user

import co.getaim.domain.UserDomain
import co.getaim.storage.persistence.mysql.entity.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity(

    @Embedded
    var information: UserInformation,

    @AttributeOverride(name = "value", column = Column(name = "password", nullable = false))
    @Embedded
    var password: Password,

    id: Long = 0L
) : BaseEntity(id) {

    val userId: String
        get() = information.userId

    val username: String
        get() = information.username

    val passwordValue: String
        get() = password.value

    fun toDomain() = UserDomain(
        id = id,
        userId = userId,
        username = username,
        password = passwordValue
    )
}
