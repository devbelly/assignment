package co.getaim.storage.persistence.mysql.persistence

import co.getaim.storage.persistence.mysql.entity.wallet.WalletEntity
import co.getaim.storage.spec.WalletUpdateSpec
import org.springframework.stereotype.Service

@Service
class WalletPersistService(
    private val walletRepository: WalletRepository
) {

    fun findByUserId(userId: Long) = walletRepository.findByUserId(userId)?.toDomain()

    fun increaseMoney(spec: WalletUpdateSpec) =
        walletRepository.increaseMoney(spec)!!.toDomain()

    fun decreaseMoney(spec: WalletUpdateSpec) =
        walletRepository.decreaseMoney(spec)!!.toDomain()

    fun createWallet(userId: Long) =
        walletRepository.save(WalletEntity(userId = userId)).toDomain()
}