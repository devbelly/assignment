package co.getaim.api.service

import co.getaim.domain.transaction.TransactionDomain
import co.getaim.domain.transaction.TransactionStatus
import co.getaim.domain.transaction.TransactionType
import co.getaim.storage.persistence.mysql.entity.wallet.Money
import co.getaim.storage.persistence.mysql.persistence.TransactionPersistService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
@Transactional
class TransactionService(
    private val transactionPersistService: TransactionPersistService
) {

    fun save(userId: Long, type: TransactionType, amount: BigDecimal) : TransactionDomain {
        return transactionPersistService.save(userId, type, Money(amount))
    }

    fun findByIdOrNull(id: Long) = transactionPersistService.findByIdOrNull(id)

    fun updateStatus(id: Long, status: TransactionStatus) {
        transactionPersistService.update(id, status)
    }
}