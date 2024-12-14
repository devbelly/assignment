package co.getaim.storage.persistence.mysql.entity.wallet

import co.getaim.domain.wallet.WalletDomain
import co.getaim.storage.persistence.mysql.entity.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "wallet")
class WalletEntity(
    @Column(nullable = false)
    val userId: Long,

    @Embedded
    val money: Money = Money(),

    @Column(nullable = false)
    @Version
    val version: Long = 0L,

    id: Long = 0L,
) : BaseEntity(id) {
    fun toDomain(): WalletDomain =
        WalletDomain(
            id = id,
            version = version,
            userId = userId,
            money = money.toDomain()
        )
}