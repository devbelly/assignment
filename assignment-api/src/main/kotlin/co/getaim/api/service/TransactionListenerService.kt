package co.getaim.api.service


import co.getaim.api.dto.event.*
import co.getaim.domain.transaction.TransactionStatus
import co.getaim.domain.transaction.TransactionType
import co.getaim.exception.transaction.TxException
import co.getaim.exception.transaction.TxExceptionType.TRANSACTION_NOT_FOUNT
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation.REQUIRES_NEW
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionalEventListener
import org.springframework.transaction.support.TransactionSynchronizationManager

@Service
@Transactional
class TransactionListenerService(
    private val transactionService: TransactionService
) {

    @EventListener
    fun transactionStart(event: DepositCreatedEvent) {
        val tx = transactionService.save(event.userId, TransactionType.DEPOSIT, event.amount)
        TransactionSynchronizationManager.bindResource("transactionId", tx.id)
    }

    @EventListener
    fun transactionStart(event: WithdrawalCreatedEvent) {
        val tx = transactionService.save(event.userId, TransactionType.WITHDRAWAL, event.amount)
        TransactionSynchronizationManager.bindResource("transactionId", tx.id)
    }

    @Transactional(propagation = REQUIRES_NEW)
    @TransactionalEventListener
    fun transactionSuccess(event: DepositSucceededEvent) {
        val transaction = transactionService.findByIdOrNull(event.transactionId)
            ?: throw TxException(TRANSACTION_NOT_FOUNT)

        transactionService.updateStatus(transaction.id, TransactionStatus.COMPLETED)
    }

    @Transactional(propagation = REQUIRES_NEW)
    @TransactionalEventListener
    fun transactionSuccess(event: WithdrawalSucceededEvent) {
        val transaction = transactionService.findByIdOrNull(event.transactionId)
            ?: throw TxException(TRANSACTION_NOT_FOUNT)

        transactionService.updateStatus(transaction.id, TransactionStatus.COMPLETED)
    }

    @Transactional(propagation = REQUIRES_NEW)
    @TransactionalEventListener
    fun transactionFail(event: DepositFailedEvent) {
        val transaction = transactionService.findByIdOrNull(event.transactionId)
            ?: throw TxException(TRANSACTION_NOT_FOUNT)

        transactionService.updateStatus(transaction.id, TransactionStatus.FAILED)
    }

    @Transactional(propagation = REQUIRES_NEW)
    @TransactionalEventListener
    fun transactionFail(event: WithdrawalFailedEvent) {
        val transaction = transactionService.findByIdOrNull(event.transactionId)
            ?: throw TxException(TRANSACTION_NOT_FOUNT)

        transactionService.updateStatus(transaction.id, TransactionStatus.FAILED)
    }
}