package co.getaim.storage.spec

import java.math.BigDecimal

data class WalletUpdateSpec(
    val walletId: Long,
    val version: Long,
    val remainingMoney: BigDecimal,
    val amount: BigDecimal
)