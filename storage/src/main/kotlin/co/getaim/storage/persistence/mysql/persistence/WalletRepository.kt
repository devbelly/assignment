package co.getaim.storage.persistence.mysql.persistence

import co.getaim.storage.persistence.mysql.entity.wallet.WalletEntity
import org.springframework.data.jpa.repository.JpaRepository

interface WalletRepository : JpaRepository<WalletEntity, Long>, WalletEntityRepositorySupport {
    fun findByUserId(userId: Long): WalletEntity?
}