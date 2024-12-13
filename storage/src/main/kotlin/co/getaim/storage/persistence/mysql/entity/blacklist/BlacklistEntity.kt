package co.getaim.storage.persistence.mysql.entity.blacklist

import co.getaim.domain.blacklist.BlacklistDomain
import co.getaim.storage.persistence.mysql.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class BlacklistEntity(
    @Column(nullable = false)
    val invalidRefreshToken: String,

    id: Long = 0L,
) : BaseEntity(id) {

    fun toDomain() = BlacklistDomain(
        id = id,
        invalidRefreshToken = invalidRefreshToken
    )
}