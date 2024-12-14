package co.getaim.storage.persistence.mysql.persistence

import co.getaim.storage.persistence.mysql.entity.transaction.TransactionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionRepository : JpaRepository<TransactionEntity, Long> , TransactionEntityRepositorySupport {
    fun findByUserId(userId: Long): TransactionEntity?
}