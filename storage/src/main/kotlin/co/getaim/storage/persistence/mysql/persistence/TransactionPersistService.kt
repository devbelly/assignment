package co.getaim.storage.persistence.mysql.persistence

import co.getaim.domain.transaction.TransactionStatus
import co.getaim.domain.transaction.TransactionType
import co.getaim.storage.persistence.mysql.entity.transaction.TransactionEntity
import co.getaim.storage.persistence.mysql.entity.wallet.Money
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TransactionPersistService(
    private val transactionRepository: TransactionRepository
) {
    fun findByIdOrNull(id: Long) = transactionRepository.findByIdOrNull(id)?.toDomain()

    fun save(userId: Long, type: TransactionType, amount: Money) =
        transactionRepository.save(TransactionEntity(userId = userId, type = type, amount = amount))
            .toDomain()

    fun update(id: Long, status: TransactionStatus): Int {
        return transactionRepository.updateStatus(id, status)
    }
}