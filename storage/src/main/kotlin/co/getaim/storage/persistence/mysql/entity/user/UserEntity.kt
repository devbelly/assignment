package co.getaim.storage.persistence.mysql.entity.user

import co.getaim.domain.user.UserDomain
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

    @Column(nullable = false)
    val isAdmin: Boolean = false,

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
        password = passwordValue,
        isAdmin = isAdmin
    )

    constructor(
        userId: String,
        username: String,
        password: String,
        id: Long = 0L,
        isAdmin: Boolean = false
    ) : this(
        UserInformation(userId, username),
        Password(password),
        isAdmin,
        id
    )
}
