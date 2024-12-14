package co.getaim.api.service

import co.getaim.api.dto.DepositWalletCommand
import co.getaim.api.dto.DepositWalletResponse
import co.getaim.api.dto.WithdrawalWalletCommand
import co.getaim.api.dto.WithdrawalWalletResponse
import co.getaim.api.dto.event.*
import co.getaim.exception.wallet.WalletException
import co.getaim.exception.wallet.WalletExceptionType
import co.getaim.exception.wallet.WalletExceptionType.NOT_ENOUGH_MONEY
import co.getaim.storage.persistence.mysql.persistence.WalletPersistService
import co.getaim.storage.spec.WalletUpdateSpec
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionSynchronizationManager

@Service
@Transactional
class WalletService(
    private val walletPersistService: WalletPersistService,
    private val publisher: ApplicationEventPublisher
) {
    fun createWallet(userId: Long) {
        walletPersistService.createWallet(userId)
    }

    fun increaseMoney(userId: Long, request: DepositWalletCommand): DepositWalletResponse {

        publisher.publishEvent(DepositCreatedEvent(userId, request.amount))

        val id = TransactionSynchronizationManager.getResource("transactionId") as Long

        return try {
            val wallet = walletPersistService.findByUserId(userId)
                ?: throw WalletException(WalletExceptionType.NOT_FOUND_WALLET)

            val spec = WalletUpdateSpec(
                wallet.id,
                wallet.version,
                wallet.money.price,
                request.amount
            )

            val newWallet = walletPersistService.increaseMoney(spec)

            publisher.publishEvent(DepositSucceededEvent(id))

            DepositWalletResponse(
                userId = userId,
                walletId = newWallet.id,
                money = newWallet.money.price
            )
        } catch (ex: Exception) {
            publisher.publishEvent(DepositFailedEvent(id))
            throw ex
        }
    }

    fun decreaseMoney(userId: Long, request: WithdrawalWalletCommand): WithdrawalWalletResponse {

        publisher.publishEvent(WithdrawalCreatedEvent(userId, request.amount))

        val id = TransactionSynchronizationManager.getResource("transactionId") as Long

        return try {
            val wallet = walletPersistService.findByUserId(userId)
                ?: throw WalletException(WalletExceptionType.NOT_FOUND_WALLET)

            val spec = WalletUpdateSpec(
                wallet.id,
                wallet.version,
                wallet.money.price,
                request.amount
            )

            if(wallet.money.price < request.amount) {
                throw WalletException(NOT_ENOUGH_MONEY)
            }

            val newWallet = walletPersistService.decreaseMoney(spec)

            publisher.publishEvent(WithdrawalSucceededEvent(id))

            WithdrawalWalletResponse(
                userId = userId,
                walletId = newWallet.id,
                money = newWallet.money.price
            )
        } catch (ex: Exception) {
            publisher.publishEvent(WithdrawalFailedEvent(id))
            throw ex
        }
    }
}